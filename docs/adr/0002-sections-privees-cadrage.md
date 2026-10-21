# ADR-0002 : Sections privées — cadrage et protection au niveau de CloudFront

- **Statut** : Proposée (phase de réflexion, rien n'est implémenté)
- **Date** : 2026-10-16, révisée le 2026-10-21
- **Décideurs** : Ricky

## Contexte

Demande : rendre privées les sections **IoT Platform v3/v4** et **Hypervision
v1/v2**, avec une authentification par le realm Keycloak `paas`, déjà utilisé
par les clients pour se connecter à la PaaS console.

Constats relevés le 2026-10-16 :

| Constat | Conséquence |
|---|---|
| Le site est statique : VuePress → S3 → CloudFront | Un contrôle côté front ne protège rien. La protection se fait **au niveau du CDN**. |
| Chaque section est un build VuePress indépendant, avec `base` = son `deploy_path`. Ses JS sont publiés sous `/iot-platform/3/assets/…` | Protéger `/iot-platform/*` et `/hypervision/*` couvre **tout** le contenu, sans toucher au build. |
| Les buckets sont listables en direct sur S3, et celui du staging est aussi lisible | Une protection CloudFront se contourne tant que le bucket est ouvert. |
| L'index Algolia `kuzzle` renvoie des pages `iot-platform/3/` avec la clé de recherche publique, intégrée au bundle | Exclure les chemins du crawler ne suffit pas : il faut aussi purger l'index. |
| Les distributions `E3D6RP0POLCJMM` et `E2ZCCEK9GRB49U` ont pour origine l'endpoint **website** S3 (`*.s3-website-us-west-2.amazonaws.com`), confirmé le 2026-10-21 | Incompatible avec OAC : passer à l'endpoint REST demande une CloudFront Function qui réécrit en `index.html` et gère le slash final. C'est un vrai chantier, pas une case à cocher. |
| Le sitemap racine ne contient pas d'URL IoT ; chaque section a le sien sous son chemin | Rien à faire : il sera protégé avec la section. |
| Hypervision n'est ni dans `repositories.json` ni dans `sections.json` | La section est à créer de toute façon. |

Une partie de cette documentation est publique aujourd'hui. **C'est accepté** :
le but est de protéger à partir de la mise en place de l'évolution, pas de
corriger l'existant avant.

## Points tranchés (2026-10-20)

1. **Tout le contenu des sections privées est privé**, introduction comprise. Pas
   de page publique de présentation par produit.
2. **Un seul droit** : un rôle Keycloak d'accès à la doc donne accès à toutes
   les sections privées. Sans lui, rien. Pas de droit par produit ni par
   version.
3. **Connexion par redirection OIDC** (Authorization Code + PKCE) vers le realm
   Keycloak `paas`.

## Décision proposée

**Piste A : cookies signés CloudFront**, émis après une connexion OIDC.

- Un nouveau client OIDC `documentation` est créé dans le realm `paas`, avec une
  URL de redirection par environnement (`next-docs.kuzzle.io`, `docs.kuzzle.io`).
- Un comportement CloudFront `/_auth/*` route vers une Lambda (Function URL),
  sur le même domaine, pour pouvoir poser les cookies sur ce domaine :
  - `/_auth/login` redirige vers Keycloak ;
  - `/_auth/callback` échange le code et vérifie la présence du rôle d'accès à
    la doc (nom et type : voir « Points tranchés (2026-10-21) », point 1). Si le rôle est là, la
    Lambda pose les cookies signés et redirige vers la page demandée ; sinon,
    elle mène à une page « accès refusé » ;
  - `/_auth/logout` efface les cookies et termine la session Keycloak.
- Comme il n'y a qu'un seul droit, **un seul jeu de cookies** suffit, sans
  découpage par `Path`. CloudFront ne les vérifie que sur les comportements
  privés (`/iot-platform/*`, `/hypervision/*`), protégés par un key group.
- Sans cookies valides, CloudFront répond 403 avant toute Function (relevé le
  2026-10-21) : ce 403 sert la page « connexion requise » à l'URL demandée,
  qui renvoie vers `/_auth/login` (ADR-0003 du repo d'infra, voir
  [ADR-0004](0004-page-d-accueil-catalogue.md)).
- Révocation : elle n'agit qu'à l'expiration du cookie. Un retrait du rôle prend
  donc effet au plus tard après la durée du cookie.

Le Kuzzle du PaaS n'intervient pas à l'exécution : seul Keycloak est appelé. La
doc reste accessible même si le Kuzzle du PaaS est indisponible.

## Pistes écartées

- **B. Lambda@Edge, ou CloudFront Function avec un jeton HMAC maison** : sa
  souplesse (révocation immédiate, droits fins) ne sert à rien avec un droit
  unique.
- **C. Sous-domaine privé servi depuis le PaaS** (oauth2-proxy) : deux domaines
  et un deuxième pipeline. Reste le repli si A bute sur le passage en OAC.
- **D. Protection uniquement côté front** : ce n'est pas une protection.
- **Formulaire qui appelle `auth:login` sur le Kuzzle du PaaS** : le site
  manipulerait des mots de passe, sans MFA ni SSO, et il faudrait ouvrir CORS.

## À faire avec l'évolution

1. Fermer l'accès direct aux buckets et passer les origines en OAC : endpoint
   REST, plus une CloudFront Function qui réécrit en `index.html` et gère le
   slash final. Accorder `s3:ListBucket` à CloudFront pour qu'un fichier absent
   réponde 404 et non 403.
2. Purger les pages des sections privées de l'index Algolia, et les exclure du
   crawler.
3. `Cache-Control: private` et `noindex` sur les sections privées.
4. Exclure les sections privées de `dead_links.workflow.yml`, ou lui donner un
   accès.

## Points tranchés (2026-10-21)

1. **Le rôle est un rôle de client `documentation`, et ne porte pas le préfixe
   `kuzzleprofile-`.** Le plugin keycloak du PaaS lit
   `access_token.realm_access.roles`, garde ceux qui commencent par
   `kuzzleprofile-` et en fait **les profils Kuzzle de l'utilisateur** à chaque
   connexion (`kuzzle-plugin-keycloak`, `index.ts`, `extractor` ;
   `kuzzle-plugin-auth-openid`, `getKuzzleProfiles`, `updateUser`). Un rôle de
   realm `kuzzleprofile-documentation` attribué à tous les clients ajouterait
   donc un profil `documentation` à chaque utilisateur du Kuzzle du PaaS. Si ce
   profil n'y existe pas, la mise à jour de l'utilisateur échoue très
   probablement, et la connexion à la PaaS console avec. Un rôle de client
   (`resource_access.documentation.roles`) n'est pas lu par le plugin.
   Retenu : rôle de client `reader` sur le client `documentation`.
2. **Tous les clients du PaaS ont accès.** Le rôle est ajouté au rôle composite
   par défaut du realm (`default-roles-paas`) : chaque utilisateur l'a
   automatiquement, et restreindre plus tard ne demande de changer qu'un seul
   endroit.
3. **Cookie de 24 h, sans renouvellement silencieux.** À l'expiration, le retour
   par Keycloak est transparent tant que la session SSO du realm est active, sans
   ressaisir de mot de passe. Un retrait du rôle prend effet en 24 h au plus.
4. **Un bouton « Se déconnecter »**, qui efface les cookies et termine la session
   Keycloak (déconnexion initiée par le client). Sans ce second point, le clic
   suivant reconnecterait sans rien demander. Conséquence assumée : se
   déconnecter de la doc déconnecte aussi de la PaaS console, puisque c'est le
   même realm.
5. **« Demander un accès » mène au formulaire de contact de kuzzle.io.**
6. **Le code (Lambda, CloudFront Functions) et l'infra vivent dans un repo
   privé**, voir [ADR-0005](0005-repo-prive-pour-l-infra.md).
7. **Le client `documentation` est confidentiel** : son secret est gardé par la
   Lambda, dans Secrets Manager. PKCE est activé en plus.
8. **Ordre** : l'infra en code passe d'abord
   ([ADR-0005](0005-repo-prive-pour-l-infra.md)). Aucun changement de
   CloudFront pour les sections privées avant que les distributions de la doc
   soient importées.

## Questions ouvertes

1. Recherche pour les utilisateurs connectés (clé Algolia sécurisée générée par
   `/_auth/*`) : plus tard, a priori.

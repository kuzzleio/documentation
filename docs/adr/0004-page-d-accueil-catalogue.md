# ADR-0004 : Une page d'accueil publique qui présente les produits, publics et privés

- **Statut** : En attente (retour de la QA, depuis le 2026-10-21)
- **Date** : 2026-10-16, révisée le 2026-10-21
- **Décideurs** : Ricky
- **Précise** : [ADR-0002](0002-sections-privees-cadrage.md)

## Contexte

Depuis le commit `42deca1d`, la racine du site, le logo et le bouton de la
page 404 mènent à `/iot-platform/4/`. Si IoT v4 devient privé, **tout visiteur
anonyme** de docs.kuzzle.io atterrit sur un login.

Il faut donc une page d'accueil publique, qui laisse voir ce qui est public et
signale ce qui ne l'est pas.

Le framework a déjà des grilles de tuiles : `Home.vue` (classes `Tiles-*`),
`PluginsIndex.vue`, `ModulesIndex.vue`.

## Décision proposée

1. **La racine `/` devient un catalogue de produits** sous forme de tuiles :
   IoT Platform, Hypervision, Kuzzle (backend), SDKs, Plugins, Modules, PaaS…
   La racine, le logo et la page 404 y mènent. La redirection de `42deca1d`
   est retirée.
2. **Une tuile privée porte un badge** (cadenas + « Accès client ») et une ligne
   d'explication. Le clic mène à la section.
3. **Le vrai point de contrôle est le serveur, pas le clic.** Les liens profonds
   (Google, URL partagée, recherche, menu du haut, liens entre sections)
   contournent la page d'accueil. CloudFront refuse donc tout accès sans
   cookies signés valides (ADR-0002), et sert alors la page « connexion
   requise » à l'URL demandée, en 403.
4. **La page « connexion requise » joue le rôle de la boîte de dialogue envisagée** : elle dit
   que la section est réservée aux clients, qu'on va être redirigé vers la
   connexion du PaaS (realm Keycloak `paas`, le même compte que la PaaS console),
   et propose un bouton « Continuer » et un lien « Demander un accès ».
   Un utilisateur connecté mais sans le rôle d'accès à la doc
   arrive sur une page « accès refusé » avec le même lien.
5. **Pas de dialogue modale au clic sur la tuile.** Elle ferait doublon avec la
   page « connexion requise », et ajouterait un clic à chaque visite d'un utilisateur déjà
   connecté. Le front ne peut pas savoir s'il est connecté : les cookies signés
   sont `HttpOnly`. Si on veut adapter les tuiles (« Ouvrir » au lieu de « Se
   connecter »), la Lambda pose en plus un cookie non sensible lisible en JS
   (`docs_session=1`) : un simple booléen, puisqu'il n'y a qu'un seul droit.
   Il ne sert qu'à l'affichage, jamais au contrôle.
6. Le même badge apparaît dans le sélecteur de produit du menu du haut
   (`TopMenu.vue`, « Kuzzle IoT v4 ▾ ») et dans le sélecteur de version.

## Conséquences

### Positives
- Un visiteur anonyme a toujours une page publique.
- Un seul parcours d'authentification, quel que soit le point d'entrée.
- Réutilise les composants de tuiles existants.

### Négatives
- Annule le choix de `42deca1d` (atterrir directement sur IoT v4), qui avait
  été fait pour mettre IoT en avant. La tuile IoT doit donc être la plus visible
  du catalogue.

## Alternatives écartées

- **Dialogue modale au clic, sans contrôle serveur** : ne protège pas les liens
  profonds, et un modal n'est pas une protection.
- **Garder la racine sur IoT v4** : un login comme page d'accueil du site public.

## Points tranchés (2026-10-20)

- Pas de page publique de présentation par produit privé : tout le chemin de la
  section est privé. La tuile du catalogue est donc la seule présentation
  publique du produit, et doit se suffire à elle-même.

## Points tranchés (2026-10-21)

Tranchés dans [ADR-0002](0002-sections-privees-cadrage.md), « Points tranchés
(2026-10-21) » :

- **Un bouton « Se déconnecter » dans le header** (point 4). Il efface les
  cookies et termine la session Keycloak : se déconnecter de la doc déconnecte
  aussi de la PaaS console.
- **« Demander un accès » mène au formulaire de contact de kuzzle.io** (point 5).

## Mise en œuvre (2026-10-21)

- Catalogue : `ProductCatalog.vue`, alimenté par `CATALOG` dans `products.ts`.
  Pas de tuile Hypervision tant que la section n'existe pas.
- Une section est marquée privée par `private: true` dans `products.ts`. C'est
  de l'affichage seulement : la liste qui fait foi est `private_paths` dans
  static-sites-infra, et les deux doivent rester alignées.
- Le cookie `docs_session` (point 5) est posé et effacé par la Lambda
  `/_auth/*` ; le front le lit dans `auth.ts` pour afficher « Sign in » ou
  « Open » sur les tuiles, et le bouton « Sign out » dans le header.
- Pas de sélecteur de version séparé (point 6) : les versions d'IoT sont des
  entrées du sélecteur de produit, qui porte le badge.

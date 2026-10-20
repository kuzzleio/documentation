# ADR-0003 : Tout le chantier se valide d'abord sur next-docs.kuzzle.io

- **Statut** : Acceptée (Ricky, 2026-10-21)
- **Date** : 2026-10-16, révisée le 2026-10-20
- **Décideurs** : Ricky
- **Précise** : [ADR-0002](0002-sections-privees-cadrage.md)

## Contexte

Le chantier touche l'infra (CloudFront, S3, Lambda) et le parcours de tout
visiteur (page d'accueil, login). Une erreur en prod rend la doc inaccessible
aux clients, ou au contraire laisse fuiter ce qu'on croyait protégé.

Le staging existe : la branche `develop` du framework déploie dans le bucket
`docs-next.kuzzle.io`, distribution `E2ZCCEK9GRB49U`.

Constats relevés le 2026-10-16 :
- le domaine du staging est **`next-docs.kuzzle.io`**, qui répond 200 ;
- `docs-next.kuzzle.io` est le nom du bucket, pas un domaine : il ne résout que
  par l'enregistrement joker `*.kuzzle.io`, qui pointe vers une validation ACM
  (corrigé le 2026-10-21) ;
- le bucket du staging est **listable et lisible publiquement**.

## Décision

1. Toute la protection (OAC, cookies signés, CloudFront Function, Lambda
   `/_auth/*`, page d'accueil, login) est d'abord déployée et validée sur
   `next-docs.kuzzle.io`.
2. Le staging a **sa propre configuration** : key group, comportements, URL de
   redirection `next-docs.kuzzle.io` sur le client OIDC `documentation` du realm
   `paas`. Rien n'est partagé avec la prod, pour pouvoir tout casser sans risque.
3. La fermeture de l'accès direct au bucket du staging fait partie de ce qui se
   valide sur le staging : tant que le bucket est ouvert, la protection se
   contourne, et la recette ne prouve rien.
4. La prod ne bascule qu'après une recette sur le staging, et **avant** le
   premier déploiement d'IoT v4 en prod.

## Conséquences

### Positives
- La prod n'est touchée qu'une fois le parcours éprouvé.
- Le staging devient lui-même protégé, ce qu'il doit être de toute façon : il
  porte de la doc pas encore sortie.

### Négatives
- Deux configurations CloudFront à tenir alignées à la main, tant que l'infra
  n'est pas décrite en code.

### Points à éclaircir
- Le nom du domaine : documenter `next-docs.kuzzle.io` partout. Il n'y a pas
  d'enregistrement `docs-next.kuzzle.io` à réparer.
- Le commit `42deca1d` (racine du site → `/iot-platform/4/`) est déjà sur
  `develop`, donc sur le staging : voir
  [ADR-0004](0004-page-d-accueil-catalogue.md).

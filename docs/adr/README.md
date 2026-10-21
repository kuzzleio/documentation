# Architecture Decision Records

Ce dossier conserve les décisions structurantes du framework de documentation
(`kuzzleio/documentation`). Une ADR répond à **pourquoi** on a fait un choix,
pas à *qui fait quoi* (ça, ce sont les issues GitHub).

Système repris de `kuzzle-admin-console` (`docs/adr/`), voir
[ADR-0001](0001-systeme-de-suivi.md).

## Règles

- Une ADR est **immuable** une fois acceptée. On ne la réécrit pas : on en écrit
  une nouvelle qui la remplace, et on passe l'ancienne en `Remplacée par ADR-XXX`.
- Une ADR `Proposée` peut, elle, être retravaillée librement tant qu'elle n'est
  pas acceptée.
- Numérotation séquentielle, jamais réutilisée.
- Format court. Si une ADR fait plus de deux écrans, c'est qu'elle mélange
  plusieurs décisions.
- On écrit l'ADR **au moment où la décision est prise**, pas après coup.
- Les constats sont **datés** : ce qui est vrai de l'infra aujourd'hui ne le sera
  plus forcément demain.

## Statuts

`Proposée` → `Acceptée` → (`Remplacée par ADR-XXX` | `Abandonnée`)

`En attente` : décision prise et mise en œuvre, en attente d'une validation
externe (QA) avant de dérouler la suite.

## Index

| # | Titre | Statut |
|---|---|---|
| [0001](0001-systeme-de-suivi.md) | Système de suivi des décisions du framework de documentation | Acceptée |
| [0002](0002-sections-privees-cadrage.md) | Sections privées : cadrage et protection au niveau de CloudFront | Proposée |
| [0003](0003-valider-sur-next-docs.md) | Tout le chantier se valide d'abord sur next-docs.kuzzle.io | Acceptée |
| [0004](0004-page-d-accueil-catalogue.md) | Une page d'accueil publique qui présente les produits, publics et privés | En attente |
| [0005](0005-repo-prive-pour-l-infra.md) | Un repo privé pour l'infra de la documentation et de l'Admin Console | Acceptée |

# ADR-0001 : Système de suivi des décisions du framework de documentation

- **Statut** : Acceptée (Ricky, 2026-10-21)
- **Date** : 2026-10-16
- **Décideurs** : Ricky

## Contexte

Le chantier « documentation privée » touche à la fois le framework VuePress,
l'infra AWS (S3, CloudFront), le Kuzzle du PaaS et Keycloak. Les choix vont se
discuter sur plusieurs sessions, avec l'aide d'agents, et devront être justifiés
plus tard (« pourquoi des cookies signés et pas un proxy ? »).

Le repo n'a aujourd'hui aucun support pour ça : ni ADR, ni `CLAUDE.md`. Le
raisonnement reste dans des conversations et disparaît.

`kuzzle-admin-console` a déjà un système d'ADR rodé (70 ADR, `docs/adr/`).

## Décision

On reprend le système de `kuzzle-admin-console`, allégé :

| Support | Porte | Nature |
|---|---|---|
| `docs/adr/` | le **pourquoi** | figé une fois accepté, versionné avec le code |
| GitHub Issues | le **qui fait quoi** | assignable, jetable |

- Une décision qu'on devra justifier plus tard → **ADR**, y compris les décisions
  négatives.
- Une issue ne duplique jamais une ADR : elle **la référence**.
- Pas de `MIGRATION.md` pour l'instant : le chantier n'a pas d'inventaire à
  suivre. À créer par une nouvelle ADR si le besoin apparaît.

`docs/` est hors de `src/` : VuePress ne le construit pas et il n'est pas publié.

## Conséquences

### Positives
- Même format dans les deux repos : rien à réapprendre.
- Les ADR sont revues en PR comme du code.

### Négatives
- Discipline nécessaire pour écrire l'ADR au moment de la décision.

## Alternatives écartées

- **Tout en issues GitHub** : le raisonnement se perd dans les commentaires une
  fois l'issue fermée.
- **Un outil externe (Notion, Jira)** : désynchronisé du code.

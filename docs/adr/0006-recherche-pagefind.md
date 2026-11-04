# ADR-0006 : Remplacer Algolia par Pagefind, un index par section

- **Statut** : Proposée (prototype sur next-docs, [ADR-0003](0003-valider-sur-next-docs.md))
- **Date** : 2026-11-04
- **Décideurs** : Ricky
- **Remplace** : le point 2 de « À faire » et la question ouverte 1 de [ADR-0002](0002-sections-privees-cadrage.md)

## Contexte

- La recherche Algolia (DocSearch, index `kuzzle`) donne des résultats jugés
  peu pertinents, et dépend d'un crawler externe qui repasse à son rythme.
- Sa clé de recherche est publique, dans le bundle. L'index contient des pages
  de sections privées (constat de l'ADR-0002) : les garder cherchables
  demanderait une clé sécurisée générée par `/_auth/*`, les retirer demande de
  purger l'index et d'exclure ces chemins du crawler.
- Chaque section est une instance VuePress distincte, construite dans son
  propre job et déployée sous son propre chemin. Une recherche locale de
  VuePress (`plugin-search`, slimsearch) ne voit que l'instance courante.

## Décision proposée

1. **Pagefind, un index par instance.** Un plugin VuePress
   (`pagefind-plugin.ts`) construit l'index à la fin de chaque build, dans
   `<dest>/pagefind/`. Il part donc avec les pages, sans étape CI en plus. Seul
   l'élément `data-pagefind-body` de la mise en page (l'article) est indexé.
2. **La recherche fusionne les index dans le navigateur.** `SiteSearch.vue`
   charge l'index de la racine et y fusionne (`mergeIndex`) ceux des sections
   de la même version majeure de Kuzzle. La liste vient de
   `.repos/repositories.json` au build (`search-indexes.ts`), sans les
   sections `"production": false` en production.
3. **Les sections privées restent cherchables pour les clients connectés,
   sans rien de plus côté infra.** Leur index est sous leur chemin, donc
   derrière la même règle CloudFront que leurs pages. Il n'est demandé que si
   le cookie `docs_session` est présent, et n'est fusionné que s'il répond
   vraiment du JSON : une session expirée retire ces résultats, sans casser la
   recherche.
4. **Algolia est retiré du site** : plugin DocSearch, clés, balises
   `data-algolia-*` et `docsearch:version`.

## Conséquences

- Plus de service tiers ni de crawler : l'index suit chaque déploiement.
- La première recherche charge une petite entrée par section (une cinquantaine
  de requêtes, en parallèle), puis seulement les morceaux d'index utiles.
- L'index Algolia `kuzzle` existe toujours et reste interrogeable avec la clé
  publique, sections privées comprises : il faut le supprimer (ou au moins le
  vider) et arrêter le crawler, côté Algolia.
- Une section non reconstruite depuis ce changement n'a pas d'index : elle est
  simplement absente des résultats jusqu'à son prochain build.

## À valider sur next-docs

1. Pertinence des résultats sur les sections publiques, comparée à Algolia.
2. Temps de la première recherche avec tous les index.
3. Connecté : les résultats des sections privées apparaissent ; déconnecté,
   ils disparaissent sans erreur.

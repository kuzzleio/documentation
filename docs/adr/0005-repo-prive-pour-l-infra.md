# ADR-0005 : Un repo privé pour l'infra de la documentation et de l'Admin Console

- **Statut** : Acceptée (Ricky, 2026-10-21)
- **Date** : 2026-10-20, révisée le 2026-10-21
- **Décideurs** : Ricky
- **Précise** : [ADR-0002](0002-sections-privees-cadrage.md)
- **Priorité** : P1 du chantier, avant tout changement lié aux sections privées

## Contexte

Relevé le 2026-10-20 :

| Constat | Conséquence |
|---|---|
| L'Admin Console décrit son hébergement en Terraform dans `infra/` (branche `5-dev`) : trois modules S3 + CloudFront + Route 53, état dans `s3://kuzzle.terraform.states`. `console` et `next-console` ont été importés le 2026-09-23, avec un `plan` vide | La méthode d'import est éprouvée, mais l'infra vit dans un repo **public**. |
| La documentation n'a pas d'infra en code : buckets, distributions (`E3D6RP0POLCJMM`, `E2ZCCEK9GRB49U`) et enregistrements DNS ont été créés à la main, avant l'arrivée de l'équipe actuelle | Personne ne sait relire ni reproduire leur configuration, et le chantier va l'alourdir (key group, comportements, CloudFront Functions, Lambda). |
| Les deux applications sont des sites statiques sur le même compte AWS, avec le même bucket d'état, le même certificat `kuzzle.io` et la même zone | Un module commun peut, à terme, servir aux cinq environnements. |
| La Lambda `/_auth/*` manipulera la clé privée CloudFront et le secret du client OIDC | Ces secrets restent dans Secrets Manager, mais le code qui les utilise et la topologie n'ont pas à être publics. |

## Décision

1. Un repo **privé**, `kuzzleio/static-sites-infra`, porte le Terraform des
   deux applications, **un dossier par application** :

   ```
   admin-console/
     console/        console.kuzzle.io
     next-console/   next-console.kuzzle.io
     console-v5/     console-v5.kuzzle.io
   documentation/
     docs/           docs.kuzzle.io
     next-docs/      next-docs.kuzzle.io
   modules/          modules partagés, s'ils apparaissent (voir point 4)
   ```

2. **L'Admin Console déménage telle quelle.** La clé d'état de chaque module est
   écrite dans son `backend "s3"` (`console.kuzzle.io.tfstate`…), pas déduite du
   chemin : déplacer les dossiers ne touche pas aux états. Critère : `plan` vide
   sur les trois modules après le déménagement. L'historique de `infra/` est
   repris (`git filter-repo --subdirectory-filter infra`), pour garder la trace
   des imports du 2026-09-23.
3. **La documentation est importée telle qu'elle est servie**, avec la même
   méthode que l'Admin Console : on écrit la configuration, `terraform import`,
   et on corrige la configuration jusqu'à un `plan` vide. Aucun `apply` ne doit
   « réparer » un environnement servi. Périmètre par environnement : bucket et
   sa politique, configuration website, distribution, enregistrements Route 53.
   Les écarts trouvés (bucket du staging lisible publiquement, CNAME
   `docs-next.kuzzle.io` cassé…) sont **décrits** dans la configuration et
   documentés, pas corrigés à l'import.
4. **Pas de module commun au départ.** On mutualise après les imports, avec des
   blocs `moved`, et toujours avec un `plan` vide comme critère. Mutualiser
   pendant l'import mélangerait deux sources de différences.
5. Les **scripts de publication** (`aws s3 sync`, `kuzdoc build-and-deploy`,
   `deploy.sh`) restent dans les repos publics : un repo public ne peut pas
   appeler un workflow réutilisable d'un repo privé, et ces scripts ne font que
   copier un build dans un bucket.
6. `infra/csp.json` reste dans l'Admin Console : son serveur de preview, son
   image Docker et ses specs le lisent.
7. Le retrait de `infra/` de l'Admin Console fait l'objet d'une ADR **dans
   l'Admin Console** : ses ADR et son README y renvoient (0030, 0065, 0066,
   0069).
8. Le repo d'infra a son propre `docs/adr/`. Cette ADR y est recopiée comme
   ADR-0001, et les décisions d'infra suivantes y sont écrites.

## Conséquences

### Positives
- La doc gagne une infra relisible et reproductible **avant** qu'on en
  complexifie la configuration.
- Les sections privées se construisent ensuite par des PR Terraform relues,
  d'abord sur `next-docs`.

### Négatives
- Les contributeurs externes de l'Admin Console ne voient plus l'infra.
- Tant que le module commun n'existe pas, cinq modules quasi identiques.

## Identité de déploiement de la CI

La clé AWS utilisée par les workflows de déploiement a des droits plus larges
que ce qu'exige la publication d'un site. Le constat détaillé est dans le repo
privé (`static-sites-infra`, ADR-0001) : ce dépôt est public, et ces détails
aident à cibler le compte. C'est la règle déjà appliquée par l'Admin Console.

1. **Pendant la P1**, l'identité de déploiement et sa politique entrent dans le
   périmètre de l'import, avec la même règle : on décrit l'existant, `plan`
   vide, sans le corriger.
2. **Plus tard, par une ADR séparée** : remplacer cette clé permanente par la
   fédération OIDC de GitHub vers AWS, avec un rôle IAM par repo, limité à ses
   buckets et à sa distribution. Les secrets `AWS_*` disparaissent alors des
   repos.

## Alternatives écartées

- **L'infra de la doc dans `infra/` de l'Admin Console** : mélange deux produits
  dans un repo public.
- **Un repo d'infra par application** : deux copies du même module à tenir
  alignées.
- **Organiser par environnement** (`prod/`, `staging/`) plutôt que par
  application : les deux applications n'ont ni les mêmes environnements ni le
  même cycle de vie.

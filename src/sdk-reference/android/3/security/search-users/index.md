---
layout: sdk.html.hbs
algolia: true
title: searchUsers
description: Security:searchUsers
---
  

# searchUsers
[snippet=search-users-1]
> Callback response:
Return users matching the given filter.  

---

## searchUsers(filters, [options], callback)

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| ``filters`` | JSON Object | Filter in [Elasticsearch's Query DSL](https://www.elastic.co/guide/en/elasticsearch/reference/5.4/query-filter-context.html) format |
| ``options`` | JSON Object | Optional parameters |
| ``callback`` | function | Callback handling the response |

---

## Options

| Option | Type | Description | Default |
|---------------|---------|----------------------------------------|---------|
| ``from`` | number | Starting offset | ``0`` |
| ``queuable`` | boolean | Make this request queuable or not  | ``true`` |
| ``scroll`` | string | Start a scroll session, with a time to live equals to this parameter's value following the [Elastisearch time format](https://www.elastic.co/guide/en/elasticsearch/reference/5.0/common-options.html#time-units) | ``undefined`` |
| ``size`` | number |  Number of hits to return per result page | ``10`` |

<aside class="notice">
  To get more information about scroll sessions, please refer to the <a href="{{ site_base_path }}api-documentation/controller-document/search">API reference documentation</a>.
</aside>

---

## Callback Response

Return a JSON Object

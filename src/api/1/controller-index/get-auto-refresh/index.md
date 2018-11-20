---
layout: full.html.hbs
algolia: true
title: getAutoRefresh
---


# getAutoRefresh

{{{since "1.0.0"}}}

Returns the current `autoRefresh` status for the given index.

The `autoRefresh` flag, when set to true, forces Kuzzle to perform a
[`refresh`](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/docs-refresh.html) request immediately after each change in the storage, causing documents to be immediately visible in a search.


## Arguments

* `index`: data index


## Possible errors

- [Common errors]({{ site_base_path }}api/1/errors/#common-errors)
- [NotFoundError]({{ site_base_path }}api/1/errors/#notfounderror)

---
layout: full.html.hbs
algolia: true
title: refresh
---


# refresh

{{{since "1.0.0"}}}

Forces an immediate [reindexation](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/docs-refresh.html) of the provided index.

When writing or deleting documents in Kuzzle, the changes need to be indexed before being reflected in the search results.  
By default, this operation can take up to 1 second.

**Note:** forcing immediate refreshes comes with performance costs, and should only performed when absolutely necessary.


## Arguments

* `index`: data index to refresh


## Possible errors

- [Common errors]({{ site_base_path }}api/1/errors/#common-errors)
- [NotFoundError]({{ site_base_path }}api/1/errors/#notfounderror)

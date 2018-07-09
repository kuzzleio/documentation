---
layout: full.html
algolia: true
title: setAutoRefresh
description: Set the autorefresh flag
order: 1000
---

# setAutoRefresh(index, autorefresh, [options])


The setAutoRefresh action allow to set the autorefresh flag for the index.

Each index has an autorefresh flag.  
When set to true, each write request trigger a [refresh](https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-refresh.html) action on Elasticsearch.  
Without a refresh after a write request, the documents could not be immediately visible in search.  

<div class="alert">
A refresh operation comes with some performance costs.  
While forcing the autoRefresh can be convenient on a development or test environment,  
we recommend that you avoid using it in production or at least carefully monitor its implications before using it.
</div>

## Usage

[code-example=setAutoRefresh]

## Options

Query options.

| Option | Type | Description | Default
|--------|------|-------------|---------
| queuable | boolean | Make this request queuable or not  | true

---

## Response

[section=setAutoRefreshResponse]

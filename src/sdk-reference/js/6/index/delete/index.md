---
layout: sdk.html.hbs
algolia: true
title: delete
description: Deletes an index
algolia: true
---

# delete

Deletes a data index.

## Arguments

```javascript
delete (index, [options])
```

<br/>

| Arguments | Type   | Description      |
| --------- | ------ | ---------------- |
| `index`   | <pre>string</pre> | Index name       |
| `options` | <pre>object</pre> | Query options |

### options

Additional query options

| Property     | Type<br/>(default)    | Description   |
| -------------- | --------- | ------------- |
| `queuable` | <pre>boolean</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Resolves

Resolves to a `boolean` indicating whether the index was successfully deleted in the Elasticsearch cluster.

## Usage

[snippet=delete]

---
layout: sdk.html.hbs
title: delete
description: Deletes an index
---

# delete

Deletes a data index.

<br/>

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

Resolves if the index was successfully deleted in the Elasticsearch cluster.

## Usage

[snippet=delete]

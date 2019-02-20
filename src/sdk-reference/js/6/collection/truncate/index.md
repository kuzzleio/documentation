---
layout: sdk.html.hbs
title: truncate
description: Remove all documents from collection
---

# truncate

Removes all documents from a data collection, while keeping the associated mapping.

<br/>

```javascript
truncate (index, collection, [options])
```

<br/>

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| ``index`` | <pre>string</pre> | Index name    |
| ``collection`` | <pre>string</pre> | Collection name    |
| ``options`` | <pre>object</pre> | Query options    |

### options

Additional query options

| Property     | Type<br/>(default)    | Description   |
| -------------- | --------- | ------------- |
| `queuable` | <pre>boolean</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Resolves

Resolves when the collection is successfully truncated.

## Usage

[snippet=truncate]

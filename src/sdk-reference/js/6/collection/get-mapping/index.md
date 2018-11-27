---
layout: sdk.html.hbs
algolia: true
title: getMapping
description: Return collection mapping
algolia: true
---

# getMapping

Returns a data collection mapping.

## Arguments

```javascript
getMapping (index, collection, [options])
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

Resolves to an `object` representing the collection mapping.

## Usage

[snippet=get-mapping]

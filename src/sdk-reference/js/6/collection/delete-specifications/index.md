---
layout: sdk.html.hbs
algolia: true
title: deleteSpecifications
description: Delete validation specifications for a collection
algolia: true
---

# deleteSpecifications

Deletes validation specifications for a data collection.

## Arguments

```javascript
deleteSpecifications (index, collection, [options])
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

Resolves if successful.

## Usage

[snippet=delete-specifications]

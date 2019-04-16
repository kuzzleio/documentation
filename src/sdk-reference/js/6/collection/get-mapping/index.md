---
layout: sdk.html.hbs
title: getMapping
description: Return collection mapping
---

# getMapping

Returns the collection mapping.

<br/>

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

Example :
```js
{
  "<index>": {             // The provided <index>
    "mappings": {
      "<collection>": {    // The provided <collection>
        // The actual mapping of the collection starts here:
        "properties": {
          "field1": {
            "type": "integer"
          },
          "field2": {
            "type": "keyword"
          },
          "field3": {
              "type":   "date",
              "format": "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis"
          }
        }
      }
    }
  }
}
```

## Usage

[snippet=get-mapping]

---
layout: full.html.hbs
algolia: true
title: getUserMapping
---


# getUserMapping

{{{since "1.0.0"}}}

Gets the mapping of the internal users collection.


## Response

Returns the internal profiles mapping, using [Elasticsearch mapping format](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/mapping.html).

```javascript
{
  "status": 200,                     
  "error": null,                     
  "controller": "security",
  "action": "getUserMapping",
  "requestId": "<unique request identifier>",
  "result": {
    "mapping": {
      // ...
    }
  }
}
```

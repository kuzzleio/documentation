---
layout: full.html.hbs
algolia: true
title: getRoleMapping
---



# getRoleMapping

{{{since "1.0.0"}}}

Gets the mapping of the internal security roles collection.


## Response

Returns the internal profiles mapping, using [Elasticsearch mapping format](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/mapping.html).

```javascript
{
  "status": 200,                     
  "error": null,                     
  "controller": "security",
  "action": "getRoleMapping",
  "requestId": "<unique request identifier>",
  "result": {
    "mapping": {
      // ...
    }
  }
}
```

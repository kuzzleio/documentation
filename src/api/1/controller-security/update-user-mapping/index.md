---
layout: full.html.hbs
algolia: true
title: updateUserMapping
---


# updateUserMapping

{{{since "1.0.0"}}}

Updates the internal user storage mapping.



## Body properties

* `properties`: mapping definition using [Elasticsearch mapping format](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/mapping.html)

---

## Response

Returns an acknowledgement.

```javascript
{
  "status": 200,                     
  "error": null,                     
  "action": "updateUserMapping",
  "controller": "security",
  "requestId": "<unique request identifier>",
  "result": {
    "acknowledged": true
  },
}
```

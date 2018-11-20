---
layout: full.html.hbs
algolia: true
title: updateMapping
---


# updateMapping

{{{since "1.0.0"}}}

Updates a data collection mapping.


## Arguments

* `collection`: data collection
* `index`: data index


## Response

```js
{
  "status": 200,
  "error": null,
  "index": "<index>",
  "collection": "<collection>",
  "action": "updateMapping",
  "controller": "collection",
  "requestId": "<unique request identifier>",
  "result": {}
}
```

---

## Possible errors

- [Common errors]({{ site_base_path }}api/1/errors/#common-errors)
- [NotFoundError]({{ site_base_path }}api/1/errors/#notfounderror)

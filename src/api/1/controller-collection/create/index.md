---
layout: full.html.hbs
algolia: true
title: create
---


# create

{{{since "1.0.0"}}}

Creates a new [collection]({{ site_base_path }}guide/1/essentials/persisted), in the provided `index`.  

{{{since "1.3.0"}}}

You can also provide an optional body with a data mapping that allow you to exploit the full capabilities of our persistent data storage layer.

This method will only update the mapping if the collection already exists.


## Arguments

* `collection`: data collection to create
* `index`: data index that will host the new data collection


## Response

Returns a confirmation that the collection is being created:

```javascript
{
  "status": 200,
  "error": null,
  "index": "<index>",
  "collection": "<collection>",
  "controller": "collection",
  "action": "create",
  "requestId": "<unique request identifier>",
  "result": {
    "acknowledged": true
  }
}
```

---

## Possible errors

- [Common errors]({{ site_base_path }}api/1/errors/#common-errors)
- [PreconditionError]({{ site_base_path }}api/1/errors/#preconditionerror)

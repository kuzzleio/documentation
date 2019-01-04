---
layout: full.html.hbs
title: list
---

# list

{{{since "1.0.0"}}}

Returns the list of data collections associated to a provided data index.

The returned list is sorted in alphanumerical order.  

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/<index>/_list[?type=<all|stored|realtime>][&from=0][&size=42]
Method: GET
```

### Other protocols


```js
{
  "index": "<index>",
  "controller": "collection",
  "action": "list",
  "type": "stored",
  "from": 0,
  "size": 42
}
```

---

## Arguments


* `collection`: data collection
* `index`: data index

### Optional:

* `from` and `size`: response pagination
* `type`: filters the returned collections. Allowed values: `all`, `stored` and `realtime` (default : `all`).  

---

## Response

Returns an array of objects, each one of those describing a collection, using the following properties:

* `name`: data collection name
* `type`: data collection type (either `stored` or `realtime`)

Example:

```js
{
  "status": 200,
  "error": null,
  "index": "<index>",
  "controller": "collection",
  "action": "list",
  "requestId": "<unique request identifier>",
  "result": {
    "collections": [
      {
        "name": "realtime_1", "type": "realtime"
      },
      {
        "name": "realtime_2", "type": "realtime"
      },
      {
        "name": "realtime_...", "type": "realtime"
      },
      {
        "name": "realtime_n", "type": "realtime"
      },
      {
        "name": "stored_1", "type": "stored"
      },
      {
        "name": "stored_2", "type": "stored"
      },
      {
        "name": "stored_...", "type": "stored"
      },
      {
        "name": "stored_n", "type": "stored"
      }
    ],
    "type": "all"
  }
}
```

---

## Possible errors

- [Common errors]({{ site_base_path }}api/1/essentials/errors/#common-errors)
- [NotFoundError]({{ site_base_path }}api/1/essentials/errors/#notfounderror)

---
layout: full.html.hbs
algolia: true
title: count
---


# count

{{{since "1.0.0"}}}

Returns the number of other connections sharing the same subscription.


## Body properties

* `roomId`: subscription identifier, returned by Kuzzle during upon a successful subscription

---

## Response

Returns an object with the following properties:

* `count`: number of active connections using the same provided subscription
* `roomId`: subscription identifier

```js
{
  "status": 200,
  "error": null,
  "index": null,
  "collection": null,
  "controller": "realtime",
  "action": "count",
  "requestId": "<unique request identifier>",
  "result": {
    "roomId": "<unique Kuzzle room identifier>",
    "count": 3,
  }
}
```

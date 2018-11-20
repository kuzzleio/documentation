---
layout: full.html.hbs
algolia: true
title: unsubscribe
---



# unsubscribe

{{{since "1.0.0"}}}

Removes a subscription.


## Body properties

* `roomId`: subscription identifier to room

---

## Response

Returns the removed subscription identifier.

```js
{
  "status": 200,
  "error": null,
  "index": "<data index>",
  "collection": "<data collection>",
  "controller": "realtime",
  "action": "unsubscribe",
  "volatile": {}, // subscription volatile data
  "requestId": "<unique request identifier>",
  "result": {
    "roomId": "<unique Kuzzle room identifier>"
  }
}
```

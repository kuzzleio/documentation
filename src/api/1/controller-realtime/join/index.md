---
layout: full.html.hbs
algolia: true
title: join
---

# join

{{{since "1.0.0"}}}

Join a previously created subscription.

---

## Query Syntax

### HTTP

Due to the synchronous nature of the HTTP protocol, real-time messaging is not supported

### Other protocols

```js
{
  "controller": "realtime",
  "action": "join",
  "body": {
    "roomId": "<subscription identifier>"
  },
  // optional
  "volatile": {}
}
```

---

## Arguments

### Optional:

* `volatile`: subscription information, used in [user join/leave notifications]({{site_base_path}}api/2/volatile-data)

---

## Body properties

* `roomId`: subscription identifier, returned by Kuzzle during upon a successful subscription

---

## Response

Return a `roomId` property containing the subscription identifier.

```js
{
  "status": 200,
  "error": null,
  "index": null,
  "collection": null,
  "controller": "realtime",
  "action": "subscribe",
  "volatile": {},
  "requestId": "<unique request identifier>",
  "result": {
    "roomId": "<unique Kuzzle room identifier>"
  }
}
```

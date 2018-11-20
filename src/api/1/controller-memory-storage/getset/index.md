---
layout: full.html.hbs
algolia: true
title: getset
---


# getset

{{{since "1.0.0"}}}

Sets a new value for a key, and returns its previously stored value.

[[_Redis documentation_]](https://redis.io/commands/getset)


## Arguments

* `_id`: key to get and set


## Response

Returns the previously stored value.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "getset",
  "collection": null,
  "index": null,
  "result": "key's previous value"
}
```

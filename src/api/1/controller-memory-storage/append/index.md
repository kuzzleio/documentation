---
layout: full.html.hbs
algolia: true
title: append
---


# append

{{{since "1.0.0"}}}

Appends a value to a key. If the key does not exist, it is created.

[[_Redis documentation_]](https://redis.io/commands/append)


## Arguments

* `key`: key to update or create


## Response

Returns the updated value length.

```js
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "append",
  "collection": null,
  "index": null,
  "result": 42
}
```

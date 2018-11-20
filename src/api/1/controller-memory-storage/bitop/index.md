---
layout: full.html.hbs
algolia: true
title: bitop
---


# bitop

{{{since "1.0.0"}}}

Performs a bitwise operation between multiple keys (containing string values) and stores the result in the destination key.

[[_Redis documentation_]](https://redis.io/commands/bitop)


## Arguments

* `_id`: destination key to create


## Response

Returns the new destination key length, as an integer.

```js
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "bitop",
  "collection": null,
  "index": null,
  "result": 42
}
```

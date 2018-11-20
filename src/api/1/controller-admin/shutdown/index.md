---
layout: full.html.hbs
algolia: true
title: shutdown
---


# shutdown

{{{since "1.4.0"}}}

Safely stops a Kuzzle instance after all remaining requests are processed.

In a cluster environment, the shutdown action will be propagated across all nodes.


## Response

Returns a confirmation that the command is being executed.

```js
{
  "requestId": "d16d5e8c-464a-4589-938f-fd84f46080b9",
  "status": 200,
  "error": null,
  "controller": "admin",
  "action": "shutdown",
  "collection": null,
  "index": null,
  "result": { "acknowledge": true }
}
```

---
layout: full.html.hbs
algolia: true
title: resetDatabase
---


# resetDatabase

{{{since "1.4.0"}}}

Asynchronously deletes all indexes created by users. 

Neither Kuzzle internal indexes nor Plugin indexes are deleted.


## Response

Returns a confirmation that the command is being executed.

```js
{
  "requestId": "d16d5e8c-464a-4589-938f-fd84f46080b9",
  "status": 200,
  "error": null,
  "controller": "admin",
  "action": "resetDatabase",
  "collection": null,
  "index": null,
  "result": { "acknowledge": true }
}
```

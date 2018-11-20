---
layout: full.html.hbs
algolia: true
title: resetKuzzleData
---


# resetKuzzleData

{{{since "1.4.0"}}}

Asynchronously starts the following sequence, in this order:

* Invalidates and deletes all users along with their associated credentials
* Deletes all user-defined roles and profiles
* Resets the default roles and profiles to their default values
* Deletes all document validation specifications

This action has no impact on Plugin and Document storages.


## Response

Returns a confirmation that the command is being executed.

```js
{
  "requestId": "d16d5e8c-464a-4589-938f-fd84f46080b9",
  "status": 200,
  "error": null,
  "controller": "admin",
  "action": "resetKuzzleData",
  "collection": null,
  "index": null,
  "result": { "acknowledge": true }
}
```

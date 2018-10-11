---
layout: full.html.hbs
algolia: true
title: resetKuzzleData
---

# resetKuzzleData

{{{since "1.4.0"}}}

Asynchronously start the following sequence in Kuzzle, in this order:

* Invalidate and delete all users along with their credentials
* Delete all user-defined roles and profiles
* Reset the default roles and profiles to their default values
* Delete all document validation specifications

This action has no impact on Plugin and Document storages.

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/admin/_resetKuzzleData
Method: POST
```

### Other protocols

```js
{
  "controller": "admin",
  "action": "resetKuzzleData"
}
```

---

## Response

Return a confirmation that the command is being executed.

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

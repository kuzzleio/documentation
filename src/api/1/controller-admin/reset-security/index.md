---
layout: full.html.hbs
algolia: true
title: resetSecurity
---


# resetSecurity

{{{since "1.4.0"}}}

Asynchronously deletes all users, profiles and roles.  
Then resets `anonymous`, `default` and `admin` profiles and roles to default values, specified in Kuzzle configuration files.


## Response

Returns a confirmation that the command is being executed.

```js
{
  "requestId": "d16d5e8c-464a-4589-938f-fd84f46080b9",
  "status": 200,
  "error": null,
  "controller": "admin",
  "action": "resetSecurity",
  "collection": null,
  "index": null,
  "result": { "acknowledge": true }
}
```

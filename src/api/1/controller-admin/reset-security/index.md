---
layout: full.html.hbs
algolia: true
title: resetSecurity
---

# resetSecurity

{{{since "1.4.0"}}}

Asynchronously delete all users, profiles and roles and reset `anonymous`, `default` and `admin` profiles and roles to the default specified in Kuzzle's configuration files.

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/admin/_resetSecurity
Method: POST
```

### Other protocols


```js
{
  "controller": "admin",
  "action": "resetSecurity"
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
  "action": "resetSecurity",
  "collection": null,
  "index": null,
  "result": { "acknowledge": true }
}
```

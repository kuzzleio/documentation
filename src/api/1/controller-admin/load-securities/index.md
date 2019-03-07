---
layout: full.html.hbs
title: loadSecurities
---

# loadSecurities

{{{since "1.6.6"}}}

Load roles, profiles and users into the storage layer.

The roles, profiles and users definition follow the same structure as in the body parameter of the API:

 - [createRole]({{ site_base_path }}api/1/controller-security/create-role)
 - [createProfile]({{ site_base_path }}api/1/controller-security/create-profile)
 - [createUser]({{ site_base_path }}api/1/controller-security/create-user)

**Notes:**

* The file can contain any number of roles, profiles and users.
* If a role, profile or user already exists, it will be replaced.
* Fixtures are loaded sequentially, first the roles, then the profiles and finally the users. If a failure occurs, Kuzzle immediately interrupts the sequence.

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/admin/_loadSecurities[?refresh=wait_for]
Method: POST
Body:
```

```js
{
  "roles": {
    "role-id": {
      /* role definition */
    }
  },
  "profiles": {
    "profile-id": {
      /* profile definition */
    }
  },
  "users": {
    "user-id": {
      /* user definition */
    }
  }
}
```


### Other protocols


```js
{
  "controller": "admin",
  "action": "loadSecurities",
  "body": <securities>
}
```

## Arguments

### Optional:

* `refresh`: if set to `wait_for`, Kuzzle will not respond until the fixtures are loaded

---

## Response

Returns a confirmation that the command is being executed.

```js
{
  "requestId": "d16d5e8c-464a-4589-938f-fd84f46080b9",
  "status": 200,
  "error": null,
  "controller": "admin",
  "action": "loadSecurities",
  "collection": null,
  "index": null,
  "result": { "acknowledge": true }
}
```

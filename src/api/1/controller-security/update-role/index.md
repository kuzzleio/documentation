---
layout: full.html.hbs
title: updateRole
---

# updateRole

{{{since "1.0.0"}}}

Updates a security role definition.

**Note:** partial updates are not supported for roles, this API route will replace the entire role content with the provided one.

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/roles/<_id>/_update[?refresh=wait_for]
Method: PUT  
Body:
```

```js
{
  "controllers": {
    "*": {
      "actions": {
        "*": true
      }
    }
  }
}
```

### Other protocols

```js
{
  "controller": "security",
  "action": "updateRole",
  "_id": "<roleId>",
  "body": {
    "controllers": {
      "*": {
        "actions": {
          "*": true
        }
      }
    }
  }
}
```

---

## Arguments

* `_id`: role identifier

### Optional:

* `refresh`: if set to `wait_for`, Kuzzle will not respond until the role changes are indexed

---

## Body properties

* `controllers`: [role definition]({{ site_base_path }}guide/1/essentials/security/#defining-roles)

---

## Response

Returns the updated role identifier and version number.

```javascript
{
  "status": 200,                     
  "error": null,                     
  "action": "updateRole",
  "controller": "security",
  "requestId": "<unique request identifier>",
  "result": {
    "_id": "<roleId>",
    "_version": 2
  }
}
```

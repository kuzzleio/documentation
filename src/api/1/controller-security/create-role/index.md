---
layout: full.html.hbs
title: createRole
---

# createRole

Creates a new role.

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/roles/<_id>/_create[?refresh=wait_for]
Method: POST  
Body:
```

If you want a super admin role, you would do as follow :
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

For an example, if you want your it to be a document writer, you can define it as follow :

```js
{
  "controllers": {
    "document": {
      "actions": {
        "create": true,
        "createOrReplace": true,
        "replace": true,
        "update": true,
        "delete": true
      }
    }
  }
}
```

Another example for an admin console role would be :

```js
{
  "controllers": {
    "security": {
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
  "action": "createRole",
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

* `refresh`: if set to `wait_for`, Kuzzle will not respond until the created role is indexed

---

## Body properties

* `controllers`: [role definition]({{ site_base_path }}guide/1/essentials/security/#defining-roles)

---

## Response

Returns the role creation/replacement status:

* `_id`: created/replaced role identifier
* `_source`: role definition
* `created`: always true
* `version`: always 1

```javascript
{
  "status": 200,                     
  "error": null,                     
  "result": {
    "_id": "<roleId>",
    "_version": 1,
    "created": true,
    "_source": {
      "controllers": {
        "*": {
          "actions": {
            "*": true
          }
        }
      }
    }
  }
  "requestId": "<unique request identifier>",
  "controller": "security",
  "action": "createRole"
}
```

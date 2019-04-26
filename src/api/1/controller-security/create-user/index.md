---
layout: full.html.hbs
title: createUser
---

# createUser

Creates a new user.

The body contains the user data and must have the following properties:

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/users/<_id>/_create[?refresh=wait_for]
URL(2): http://kuzzle:7512/users/_create[?refresh=wait_for]
Method: POST
Body:
```

```js
{
  "content": {
    "profileIds": ["<profileId>"],
    // additional user properties (optional)
    "fullname": "John Doe"
  },
  "credentials": {
    // example with the "local" authentication strategy
    "local": {
      username: "jdoe",
      password: "foobar"
    }
  }
}
```

### Other protocols

```js
{
  "controller": "security",
  "action": "createUser",
  "_id": "<kuid>",
  "body": {
    "content": {
      "profileIds": ["<profileId>"],
      // additional user properties (optional)
      "fullname": "John Doe"
    },
    "credentials": {
      // example with the "local" authentication strategy
      "local": {
        username: "jdoe",
        password: "foobar"
      }
    }
  }
}
```

---

## Arguments

### Optional:

* `_id`: user [kuid]({{site_base_path}}guide/1/kuzzle-depth/authentication/#the-kuzzle-user-identifier). An error is returned if the provided identifier already exists. If not provided, a random kuid is automatically generated.

---

## Body properties

* `content`: an object describing the user. Properties:
  * `profileIds`: an array of security profiles attributed to the user
  * any other property: optional additional user information
* `credentials`: describe how the new user can be authenticated. This object contains any number of properties, named after the target authentication strategy to use. Each one of these properties are objects containing the credentials information, corresponding to that authentication strategy. If left empty, the new user is created but cannot be authenticated.

---

## Response

Returns the user creation status:

* `_id`: new user kuid
* `_source`: new user content and attributed profiles
* `created`: always true
* `version`: always 1

```javascript
{
  "status": 200,
  "error": null,
  "controller": "security",
  "action": "createUser",
  "requestId": "<unique request identifier>",
  "result": {
    "_id": "<kuid>",
    "_source": {
      "profileIds": ["<profileId>"],
      "fullname": "John Doe"
    },
    "_version": 1,
    "created": true
  }
}
```

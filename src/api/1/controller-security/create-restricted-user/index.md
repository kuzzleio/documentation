---
layout: full.html.hbs
algolia: true
title: createRestrictedUser
---

# createRestrictedUser

{{{since "1.0.0"}}}

Create a new user in Kuzzle, with a preset list of security profiles.

The list of security profiles attributed to restricted users is fixed, and must be configured in the [Kuzzle configuration file]({{ site_base_path }}guide/1/essentials/configuration/).

This method allows users with limited rights to create other accounts, but blocks them from creating accounts with unwanted privileges (e.g. an anonymous user creating his own account).

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/users/<_id>/_createRestricted[?refresh=wait_for] 
Alternate URL: http://kuzzle:7512/users/_createRestricted[?refresh=wait_for]
Method: POST
Body:
```

```js
{
  "content": {
    // user additional information (optional)
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
  "action": "createRestrictedUser",
  "_id": "<kuid>",
  "body": {
    "content": {
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

* `content`: user additional information. Can be left empty.
* `credentials`: describe how the new user can be authenticated. This object contains any number of properties, named after the target authentication strategy to use. Each one of these properties are objects containing the credentials information, corresponding to that authentication strategy. If left empty, the new user is created but cannot be authenticated.

---

## Response

Return the restricted user creation status:

* `_id`: new user kuid
* `_source`: new user content and attributed profiles
* `created`: always true
* `version`: always 1

```javascript
{
  "status": 200,
  "error": null,
  "controller": "security",
  "action": "createRestrictedUser",
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

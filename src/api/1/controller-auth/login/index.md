---
layout: full.html.hbs
algolia: true
title: login
---

# login

{{{since "1.0.0"}}}

Authenticates a user.

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/_login/<strategy>[?expiresIn=<expiresIn>]
Method: POST  
Body:
```

```javascript
{
  "username": "<username>",
  "password": "<password>"
}
```

### Other protocols

```js
{
  "controller": "auth",
  "action": "login",
  "strategy": "<strategy>",
  "expiresIn": "<expiresIn>",
  "body": {
    "username": "<username>",
    "password": "<password>"
  }
}
```

---

## Arguments

* `strategy`: the name of the authentication [strategy]({{ site_base_path }}guide/1/kuzzle-depth/authentication/#authentication) used to log the user in.

### Optional:

* `expiresIn`: set the expiration duration (default: depends on [Kuzzle configuration file]({{ site_base_path }}guide/1/essentials/configuration/))
  * if a raw number is provided (not enclosed between quotes), then the expiration delay is in milliseconds. Example: `86400000`
  * if this value is a string, then its content is parsed by the [ms](https://www.npmjs.com/package/ms) library. Examples: `"6d"`, `"10h"`

---

## Body properties

Depending on the chosen authentication strategy, additional [credential arguments]({{ site_base_path }}guide/1/kuzzle-depth/authentication/#authentication) may be required.

The API request example in this page provides the necessary arguments for the [`local` authentication plugin](https://github.com/kuzzleio/kuzzle-plugin-auth-passport-local).

Check the appropriate [authentication plugin]({{ site_base_path }}plugins-reference/1/plugins-features/adding-authentication-strategy/) documentation to get the list of additional arguments to provide.

---

## Response

The result contains the following properties:

* `_id`: user's [kuid]({{ site_base_path }}guide/1/kuzzle-depth/authentication#the-kuzzle-user-identifier) 
* `jwt`: encrypted JSON Web Token, that must then be sent in the [requests headers]({{ site_base_path }}api/1/documentation/query-syntax/authorization-token/)
* `expiresAt`: token expiration date, in Epoch-millis (UTC)
* `ttl`: token time to live, in milliseconds

```javascript
{
  "status": 200,
  "error": null,
  "controller": "auth",
  "action": "login",
  "requestId": "<unique request identifier>",
  "volatile": {},
  "result": {
    "_id": "<kuid>",
    "jwt": "<JWT encrypted token>",
    "expiresAt": 1321085955000,
    "ttl": 360000
  }
}
```

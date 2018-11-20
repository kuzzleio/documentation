---
layout: full.html.hbs
algolia: true
title: login
---


# login

{{{since "1.0.0"}}}

Authenticates a user.


## Arguments

* `strategy`: the name of the authentication [strategy]({{ site_base_path }}guide/1/kuzzle-depth/authentication/#authentication) used to log the user in.

### Optional:

* `expiresIn`: set the expiration duration (default: depends on [Kuzzle configuration file]({{ site_base_path }}guide/1/essentials/configuration/))
  * if a raw number is provided (not enclosed between quotes), then the expiration delay is in milliseconds. Example: `86400000`
  * if this value is a string, then its content is parsed by the [ms](https://www.npmjs.com/package/ms) library. Examples: `"6d"`, `"10h"`


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

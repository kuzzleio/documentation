---
layout: full.html.hbs
algolia: true
title: Authentication
---


# Authentication

Kuzzle uses [Passport.js](http://Passportjs.org/) to support numerous authentication strategies, including:

- OAuth2: for authentication using providers such as GitHub or Google (click [here](https://github.com/kuzzleio/kuzzle-plugin-auth-passport-oauth) for more details).
- SAML: for authentication using providers such as OneLogin and for supporting single sign-on.
- Local: for authentication using a user's username and password managed in your own domain.

In the diagram below, we highlighted components of Kuzzle's server [architecture]({{ site_base_path }}guide/1/kuzzle-depth) that are used in the authentication process:

![read_scenario_http_overview](Authentication_Overview.png)

* The Auth Controller: to manage the authentication process.
* The Passport Wrapper: to interface between Kuzzle and the Passport.js library.
* The User and Token [Repositories](https://github.com/kuzzleio/kuzzle/tree/master/lib/api/core/models/repositories): to store user data.
* The Auth Strategy: to identify and validate a user's credential using a [dedicated plugin]({{ site_base_path }}plugins/1/essentials/strategies).


### Local Authentication Strategy

The Local Authentication Strategy, implemented in the [Passport Local Plugin](https://github.com/kuzzleio/kuzzle-plugin-auth-passport-local), authenticates a user by their username and password, which are stored in a local repository. The following diagram shows how the authentication request flows between the Client Application and Kuzzle's server components:

![auth_scenario_details_local](details-local.png)

* The Client Application calls the `login` action of the *Auth Controller*:

```javascript
{
  "controller": "auth",
  "action": "login",
  "strategy": "local",
  "body": {
    "username": "<my_username>",
    "password": "<my_password>"
  }
}
```

* The *Auth Controller* calls the `authenticate()` method of the *Passport Wrapper*.

* The *Passport Wrapper* calls the `verify()` method in the *Local Authentication Plugin*.

* The *Local Authentication Plugin* validates the credentials against the [local storage]({{ site_base_path }}plugins/1/constructors/repository) and returns the user's `kuid` if the credentials are valid.

* The *Passport Wrapper* calls the *User Repository* to get the profile data for the user with the given `kuid`.

* The user profile data is returned back to the *Auth Controller*.

* The *Auth Controller* calls the `generateToken()` method to get a [JWT Token](https://jwt.io/) for the user.

* The JWT Token is then sent back to the Client Application and should be used in subsequent requests to Kuzzle's API. Below is an example response after the authentication process is complete:

```javascript
{
  "status": 200,
  "error": null,
  "controller": "auth",
  "action": "login",
  "state": "done",
  "requestId": "ed4faaff-253a-464f-a6b3-387af9d8483d",
  "volatile": {},
  "result": {
    "_id": "user-kuid",
    "jwt": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJteV91c2VybmFtZSIsIm5hbWUiOiJKb2huIERvZSIsImFkbWluIjp0cnVlfQ.BefoyfAKzwXuGhbYe0iPeG0v9F4HmikvahqwqzQr3pE",
    "expiresAt": 1321085955000,
    "ttl": 360000
  }
}
```


## Additional Authentication Strategies

Kuzzle can implement any strategy supported by Passport.js. For more information, please refer to the [Plugins Reference]({{ site_base_path }}plugins/1/essentials/strategies).

---

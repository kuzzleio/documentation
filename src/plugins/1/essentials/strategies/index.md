---
layout: full.html.hbs
algolia: true
title: Strategies
order: 500
---

# Strategies

Plugins can add new authentication strategies to Kuzzle.  
For example, our official [OAUTH2 Authentication plugin](https://github.com/kuzzleio/kuzzle-plugin-auth-passport-oauth) adds OAUTH2 support to Kuzzle.

All authentication strategies supported by [Passport.js](http://passportjs.org/) can be integrated to Kuzzle.

---

## Registering authentication strategies

[Passport.js](http://passportjs.org) provides a wide range of authentication strategies.  
Custom authentication strategies can also be implemented by subclassing the abstract [Passport Strategy](https://github.com/jaredhanson/passport-strategy) class.

To register strategies to Kuzzle, a `authenticators` object property must be exposed by the plugin, for instance:

```
this.authenticators = {
  Local: require('passport-local'),
  Oauth2: require('passport-oauth2')
};
```

---

## Credentials security

User credentials are very sensitive data, and these must be properly isolated to prevent security vulnerabilities.  
To do so, Kuzzle guarantees that it never interprets, modifies, or stores credentials information. 

Instead, Kuzzle:

* provides a global user unique identifier (referred from now on as the user's [kuid]({{ site_base_path }}guide/1/kuzzle-depth/authentication/#the-kuzzle-user-identifier-kuid)), giving the possibility to a user to authenticate with multiple strategies
* entrusts implemented strategies with credentials protection, validation, verification and storage

---

## Managing credentials

There are two ways of interfacing credentials management:

* statically, by exposing a `strategies` object
* dynamically, by using the dedicated [strategy accessors]({{ site_base_path }}plugins/1/plugins-context/accessors/#strategies)

Whether strategies are added statically or dynamically, the same object structure is expected:

* `config`: an object containing the strategy configuration
  * `authenticator`: One of the exposed [authenticators]({{ site_base_path }}plugins/1/essentials/strategies/#registering-authentication-strategies-default) name
  * `constructor`: {{{deprecated "1.4.0"}}} (use the `authenticator` property instead) The constructor of the Passport.js strategy. Does not support [dynamic strategy registration]({{ site_base_path }}plugins/1/plugins-context/accessors/#strategies)
* `methods`: an object containing the list of exposed methods
  * `create`: the name of the exposed [`create` function]({{ site_base_path }}plugins/1/essentials/strategies/#create-default)
  * `delete`: the name of the exposed [`delete` function]({{ site_base_path }}plugins/1/essentials/strategies/#delete-default)
  * `exists`: the name of the exposed [`exists` function]({{ site_base_path }}plugins/1/essentials/strategies/#exists-default)
  * `update`: the name of the exposed [`update` function]({{ site_base_path }}plugins/1/essentials/strategies/#update-default)
  * `validate`: the name of the exposed [`validate` function]({{ site_base_path }}plugins/1/essentials/strategies/#update-default)
  * `verify`: the name of the exposed [`verify` function]({{ site_base_path }}plugins/1/essentials/strategies/#verify-default)
  * (optional) `afterRegister`: the name of the exposed [`afterRegister` function]({{ site_base_path }}plugins/1/essentials/strategies/#optional-afterregister-default)
  * (optional) `getById`: the name of the exposed [`getById` function]({{ site_base_path }}plugins/1/essentials/strategies/#optional-getbyid-default)
  * (optional) `getInfo`: the name of the exposed [`getInfo` function]({{ site_base_path }}plugins/1/essentials/strategies/#optional-getinfo-default)

Even though each strategy must declare its own set of properties, the same strategy method can be used by multiple strategies.


### Optional config properties

The `config` object may contain the following optional properties:

  * `authenticateOptions`: additional options to be provided to the Passport's [authenticate method](http://passportjs.org/docs/authenticate).
  * `fields`: an array of field names accepted by the plugin to validate credentials. The list is informative only, meant to be used by the [getAllCredentialFields]({{ site_base_path }}api/1/controller-security/get-all-credential-fields/) and the [getCredentialFields]({{ site_base_path }}api/1/controller-security/get-credential-fields) API methods.
  * `strategyOptions`: options to be provided to the Passport.js strategy constructor

---

## create

The `create` function adds credentials to a user.

For security reasons, plugins are entirely responsible of how credentials are managed, storage included: Kuzzle does not read, modify, or store credentials.

If needed, Kuzzle exposes a secure and isolated storage space for each plugin. It can be accessed using the [Repository]({{ site_base_path }}plugins/1/plugins-context/constructors/#repository) constructor.

### Arguments

`create (request, credentials, kuid, strategy)`

* `request`: the [Request]({{ site_base_path }}plugins/1/plugins-context/constructors/#request) object asking for credentials creation
* `credentials`: new credentials to create, already validated by this strategy's [validate](#validate) function
* `kuid`: user's [kuid]({{ site_base_path }}guide/1/kuzzle-depth/authentication/#the-kuzzle-user-identifier-kuid)
* `strategy`: authentication strategy used by these credentials

### Returned value

The `create` function must return a promise, resolving to an object. The content of that object depends on this authentication strategy; usually a feedback about the created credentials is expected. That object can be left empty.

<div class="alert alert-warning">The object resolved by the promise is directly forwarded to the originating user. For security reasons, it must only contain <strong>non sensitive</strong> information.</div>

---

## delete

The `delete` function deletes a user's credentials.

### Arguments

`delete (request, kuid, strategy)`

* `request`: the [Request]({{ site_base_path }}plugins/1/plugins-context/constructors/#request) object asking for credentials deletion
* `kuid`: user's [kuid]({{ site_base_path }}guide/1/kuzzle-depth/authentication/#the-kuzzle-user-identifier-kuid)
* `strategy`: authentication strategy name

### Returned value

The `delete` function must return a promise. The resolved value is not used.

---

## exists

The `exists` function checks whether a user is known to the authentication strategy.

### Arguments

`exists (request, kuid, strategy)`

* `request`: the [Request]({{ site_base_path }}plugins/1/plugins-context/constructors/#request) object representing the `exists` API call
* `kuid`: user's [kuid]({{ site_base_path }}guide/1/kuzzle-depth/authentication/#the-kuzzle-user-identifier-kuid)
* `strategy`: authentication strategy name

### Returned value

The `exists` function must return a promise, resolving to a boolean representing the result of the user existence check.

---

## update

The `update` function updates a user's credentials.

### Arguments

`update (request, credentials, kuid, strategy)`

* `request`: the [Request]({{ site_base_path }}plugins/1/plugins-context/constructors/#request) object asking for credentials update
* `credentials`: updated credentials, already validated by this strategy's [validate](#validate) function
* `kuid`: user's [kuid]({{ site_base_path }}guide/1/kuzzle-depth/authentication/#the-kuzzle-user-identifier-kuid)
* `strategy`: authentication strategy name

### Returned value

The `update` function must return a promise, resolving to an object. The content of that object depends on this authentication strategy; usually a feedback about the updated credentials is expected. That object can be left empty.

<div class="alert alert-warning">The object resolved by the promise is directly forwarded to the originating user. For security reasons, it must only contain <strong>non sensitive</strong> information.</div>

---

## validate

The `validate` function verifies that credentials are well-formed.

### Arguments

`validate (request, credentials, kuid, strategy, isUpdate)`

* `request`: the [Request]({{ site_base_path }}plugins/1/plugins-context/constructors/#request) object triggering a credentials verification
* `kuid`: user's [kuid]({{ site_base_path }}guide/1/kuzzle-depth/authentication/#the-kuzzle-user-identifier-kuid)
* `strategy`: authentication strategy name
* `isUpdate`: a boolean telling whether the request is an update to credentials (if `true`, credentials may be purposedly incomplete)

### Returned value

The function `validate` must return a promise. The resolved value, if there is one, is ignored.

---

## verify

The [verify](http://passportjs.org/docs/configure) function authenticates a user.

The number of arguments taken by the `verify` function depends on the authentication strategy.  
For instance, a `local` authentication strategy requires that the `verify` function validates both a user name and a password, so these two arguments will have to be provided to the `verify` function.

### Arguments

`verify(payload, ...)`

* `payload`: login request made to passport
* `...`: additional arguments depending on the authentication strategy

The `payload` argument is a simple JSON object that contains the following attributes:

* `original`: the [Request]({{ site_base_path }}plugins/1/plugins-context/constructors/#request) object representing the login request
* `query`: direct link to `original.input.args`, containing the optional request arguments
* `body`: direct link to `original.input.body`, containing the request body content

### Returned value

The `verify` function must return a promise, resolving to an object with the following properties:

* `kuid`: if the authentication succeeds, this property must be set to the user's [kuid]({{ site_base_path }}guide/1/kuzzle-depth/authentication/#the-kuzzle-user-identifier-kuid). Otherwise, this must be set to `null`
* `message`: if `kuid` is set to `null` (authentication failed), this optional property can be set with a rejection reason

<div class="alert alert-info">A failed authentication is not an error. The returned promise should only be rejected if an actual error occurs.</div>

---

## (optional) afterRegister

The `afterRegister` function is called when the Passport.js strategy is instantiated by Kuzzle.

### Arguments

`afterRegister (strategyInstance)`

* `strategyInstance`: the Passport.js strategy instance

---

## (optional) getById

The `getById` function returns credentials information using the authentication strategy's user identifier (which may not be the [kuid]({{ site_base_path }}guide/1/kuzzle-depth/authentication/#the-kuzzle-user-identifier-kuid)).

If this function is not implemented, an empty object is returned by Kuzzle instead.

<div class="alert alert-warning">The returned information can be forwarded to users. For security reasons, it must only contain <strong>non sensitive</strong> information.</div>

### Arguments

`getById (request, id, strategy)`

* `request`: the [Request]({{ site_base_path }}plugins/1/plugins-context/constructors/#request) object asking for credentials information
* `id`: strategy's user identifier 
* `strategy`: authentication strategy name

### Returned value

The `getById` function must return a promise, resolving to an object containing credentials information. It can be left empty.

---

## (optional) getInfo

The `getInfo` function returns information about a user's credentials.

If this function is not implemented, an empty object is returned by Kuzzle instead.

<div class="alert alert-warning">The returned information can be forwarded to users. For security reasons, it must only contain <strong>non sensitive</strong> information.</div>

### Arguments

`getInfo (request, kuid, strategy)`

* `request`: the [Request]({{ site_base_path }}plugins/1/plugins-context/constructors/#request) object asking for credentials information
* `kuid`: user's [kuid]({{ site_base_path }}guide/1/kuzzle-depth/authentication/#the-kuzzle-user-identifier-kuid)
* `strategy`: authentication strategy name

### Returned value

The `getInfo` function must return a promise, resolving to an object containing credentials information. It can be left empty.

---

## Example

```javascript
module.exports = class AuthenticationPlugin {
  constructor() {}

  /**
    Required plugin initialization function
    (see the "Plugin prerequisites" section)
   */
  init (customConfig, context) {
    this.authenticators = {
      StrategyConstructor: require('some-passport-strategy')
    };

    this.strategies = {
      '<strategy name>': {
        config: {
          // Must be declared in this.authenticators
          authenticator: 'StrategyConstructor',

          // The list of fields that have to be provided in the credentials
          fields: ['login', 'password']
        },
        methods: {
          create: 'create',
          delete: 'delete',
          exists: 'exists',
          update: 'update',
          validate: 'validate',
          verify: 'verify'
        }
      }
    };
  }

  /**
   * Stores the provided credentials 
   * Must keep a link between the persisted credentials
   * and the kuid
   */
  create (request, credentials, kuid) {
    // store credentials
    return Promise.resolve({/* non sensitive credentials info */});
  }

  /**
   * Removes the user's stored credentials from
   * the plugin persistence layer
   */
  delete (request, kuid) {
    // remove credentials
    return Promise.resolve();
  }

  /**
   * Checks if user's credentials exist in the persistence layer
   */
  exists (request, kuid) {
    // check credentials existence
    return Promise.resolve(/* boolean value */);
  }

  /**
   * Updates the user's credentials information in the
   * persistence layer
   *
   * @param {KuzzleRequest} request
   * @param {object} credentials
   * @param {string} kuid
   * @returns {Promise<object>}
   */
  update (request, credentials, kuid) {
    // update credentials
    return Promise.resolve(/* non sensitive credentials info */);
  }

  /**
   * Validates credentials against the strategy rules 
   * (required fields, password strength, username uniqueness, ...)
   */
  validate (request, credentials, kuid, strategy, isUpdate) {
    // validate credentials
    return Promise.resolve(/* true|false */);
  }

  /**
   * Returns an object with the authenticated user id if successful,
   * and a reason if the authentication fails
   */
  verify (request, ...credentials) {
    const kuid = /* authentification */;

    if (kuid) {
      return Promise.resolve({kuid});
    }

    return Promise.resolve({
      kuid: null,
      message: 'Login failed - You shall not pass! Reason: ...'
    });
  }
}
```

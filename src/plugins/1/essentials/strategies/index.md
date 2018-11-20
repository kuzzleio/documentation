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


## Credentials security

User credentials are very sensitive data, and these must be properly isolated to prevent security vulnerabilities.  
To do so, Kuzzle guarantees that it never interprets, modifies, or stores credentials information. 

Instead, Kuzzle:

* provides a global user unique identifier (referred from now on as the user's [kuid]({{ site_base_path }}guide/1/kuzzle-depth/authentication/#the-kuzzle-user-identifier-kuid)), giving the possibility to a user to authenticate with multiple strategies
* entrusts implemented strategies with credentials protection, validation, verification and storage


## create

The `create` function adds credentials to a user.

For security reasons, plugins are entirely responsible of how credentials are managed, storage included: Kuzzle does not read, modify, or store credentials.

If needed, Kuzzle exposes a secure and isolated storage space for each plugin. It can be accessed using the [Repository]({{ site_base_path }}plugins/1/constructors/repository) constructor.

### Arguments

```js
create(request, credentials, kuid, strategy)
```
<br/>

| Arguments | Type | Description |
|
## delete

The `delete` function deletes a user's credentials.

### Arguments

```js
delete(request, kuid, strategy)
```
<br/>

| Arguments | Type | Description |
|
## exists

The `exists` function checks whether a user is known to the authentication strategy.

### Arguments

```js
exists(request, kuid, strategy)
```

<br/>

| Arguments | Type | Description |
|
## update

The `update` function updates a user's credentials.

### Arguments

```js
update(request, credentials, kuid, strategy)
```

<br/>

| Arguments | Type | Description |
|
## validate

The `validate` function verifies that credentials are well-formed.

### Arguments

```js
validate(request, credentials, kuid, strategy, isUpdate)
```

<br/>

| Arguments | Type | Description |
|
## verify

The [verify](http://passportjs.org/docs/configure) function authenticates a user.

The number of arguments taken by the `verify` function depends on the authentication strategy.  
For instance, a `local` authentication strategy requires that the `verify` function validates both a user name and a password, so these two arguments will have to be provided to the `verify` function.

### Arguments

```js
verify(payload, ...)
```
<br/>

| Arguments | Type | Description |
|
## (optional) afterRegister

The `afterRegister` function is called when the Passport.js strategy is instantiated by Kuzzle.

### Arguments

```js
afterRegister(strategyInstance)
```

<br/>

| Arguments | Type | Description |
|
## (optional) getById

The `getById` function returns credentials information using the authentication strategy's user identifier (which may not be the [kuid]({{ site_base_path }}guide/1/kuzzle-depth/authentication/#the-kuzzle-user-identifier-kuid)).

If this function is not implemented, an empty object is returned by Kuzzle instead.

<div class="alert alert-warning">The returned information can be forwarded to users. For security reasons, it must only contain <strong>non sensitive</strong> information.</div>

### Arguments

```js
getById(request, id, strategy)
```

<br/>

| Arguments | Type | Description |
|
## (optional) getInfo

The `getInfo` function returns information about a user's credentials.

If this function is not implemented, an empty object is returned by Kuzzle instead.

<div class="alert alert-warning">The returned information can be forwarded to users. For security reasons, it must only contain <strong>non sensitive</strong> information.</div>

### Arguments

```js
getInfo(request, kuid, strategy)
```

<br/>

| Arguments | Type | Description |
|
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

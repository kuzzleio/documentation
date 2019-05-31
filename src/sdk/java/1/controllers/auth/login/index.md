---
code: true
type: page
title: login
description: Authenticate a user
---

# login

Authenticates a user.

If this action is successful, all further requests emitted by this SDK instance will be in the name of the authenticated user, until either the authenticated token expires, the [logout](/sdk/java/1/controllers/auth/logout/) action is called, or the [jwt](/sdk/java/1/core-classes/kuzzle/constructor) property is manually unset.

## Signature

```java
String login(String, String, int expiresIn);
String login(String, String);
```
<br/>

## Arguments

| Arguments     | Type   | Description                      | Required |
| ------------- | ------ | -------------------------------- | -------- |
| `strategy`    | <pre>String</pre> | Name of the strategy to use  | yes      |
| `credentials` | <pre>String</pre> |  Credentials for that strategy            |  yes     |
| `expiresIn`   | <pre>int</pre>    |  Token expiration time, in milliseconds |  no      |

#### strategy

The name of the authentication [strategy](/core/1/guide/guides/kuzzle-depth/authentication/#authentication) used to log the user in.

Depending on the chosen authentication `strategy`, additional [credential arguments](/core/1/guide/guides/kuzzle-depth/authentication/#authentication) may be required.
The API request example in this page provides the necessary arguments for the [`local` authentication plugin](https://github.com/kuzzleio/kuzzle-plugin-auth-passport-local).

Check the appropriate [authentication plugin](/core/1/plugins/plugins/guides/strategies/overview/) documentation to get the list of additional arguments to provide.

### expiresIn

 The default value for the `expiresIn` option is defined at server level, in Kuzzle's [configuration file](/core/1/guide/guides/essentials/configuration/).

## Return

The **login** action returns an encrypted JSON Web Token, that must then be sent in the [requests headers](/core/1/api/essentials/query-syntax/).

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle error](/sdk/java/1/essentials/error-handling/).

## Usage

<<< ./snippets/login.java

---
layout: sdk.html.hbs
algolia: true
title: login
description: Authenticate a user
order: 200
---

# login

Authenticates a user.

## Signature

```java
String login(
  String,
  String,
  int
);
String login(
  String,
  String
);
```

## Arguments

| Arguments    | Type    | Description | Required
|--------------|---------|-------------|----------
| ``strategy`` | String | the name of the strategy to use    | yes
| ``credentials`` | String | the json credentials | yes
| ``expiresIn`` | int | expiration time in milliseconds | no

#### ***strategy***

The name of the authentication [strategy]({{ site_base_path }}guide/kuzzle-depth/authentication/#authentication) used to log the user in.

Depending on the chosen authentication `strategy`, additional [credential arguments]({{ site_base_path}}guide/kuzzle-depth/authentication/#authentication) may be required.  
The API request example in this page provides the necessary arguments for the [`local` authentication plugin](https://github.com/kuzzleio/kuzzle-plugin-auth-passport-local).

Check the appropriate [authentication plugin]({{ site_base_path }}plugins-reference/plugins-features/adding-authentication-strategy/) documentation to get the list of additional arguments to provide.

## Return

The **login** action returns an encrypted JSON Web Token, that must then be sent in the [requests headers]({{ site_base_path }}api-documentation/query-syntax/authorization-token/).

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/essentials/error-handling).

## Usage

[snippet=login]

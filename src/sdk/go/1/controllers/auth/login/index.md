---
code: true
type: page
title: Login
description: Authenticate a user
---

# Login

Authenticates a user.

## Signature

```go
func (a *Auth) Login(strategy string, credentials json.RawMessage, expiresIn *int) (string, error)
```

## Arguments

| Arguments     | Type   | Description                      | Required |
| ------------- | ------ | -------------------------------- | -------- |
| `strategy`    | string | the name of the strategy to use  | yes      |
| `credentials` | string |  the json credentials            |  yes     |
| `expiresIn`   | int    |  expiration time in milliseconds |  no      |

#### **_strategy_**

The name of the authentication [strategy](/core/1/guide/guides/kuzzle-depth/authentication/#authentication) used to log the user in.

Depending on the chosen authentication `strategy`, additional [credential arguments](/core/1/guide/guides/kuzzle-depth/authentication/#authentication) may be required.  
The API request example in this page provides the necessary arguments for the [`local` authentication plugin](https://github.com/kuzzleio/kuzzle-plugin-auth-passport-local).

Check the appropriate [authentication plugin](/core/1/plugins/essentials/strategies/) documentation to get the list of additional arguments to provide.

## Return

The **login** action returns an encrypted JSON Web Token, that must then be sent in the [requests headers](/core/1/api/essentials/query-syntax/).

## Usage

<<< ./snippets/login.go

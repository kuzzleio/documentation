---
code: true
type: page
title: checkToken
description: Checks a JWT Token's validity.
---

# checkToken

Checks a JWT Token's validity.

## Signature

```cpp
kuzzleio::token_validity* checkToken(const std::string& token);
```

## Arguments

| Arguments | Type              | Description |
| --------- | ----------------- | ----------- |
| `token`   | <pre>string</pre> | JWT token   |

## Return

A pointer to a token_validity struct which has:

| Name        | Type                          | Description                      |
| ----------- | ----------------------------- | -------------------------------- |
| valid       | <pre>bool</pre>               | Token validity                   |
| state       | <pre>const char\*</pre>       | Explain why the token is invalid |
|  expires_at | <pre>unsigned long long</pre> | Token expiration timestamp       |

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle error](/sdk/cpp/1/error-handling).

## Usage

<<< ./snippets/check-token.cpp

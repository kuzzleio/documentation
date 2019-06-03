---
code: true
type: page
title: updateMyCredentials
description: Update the current user's credentials for the specified strategy.
---

# updateMyCredentials

Update the current user's credentials for the specified strategy. The credentials to send will depend on the authentication plugin and the authentication strategy.

## Signature

```cpp
std::string updateMyCredentials(
    const std::string& strategy,
    const std::string& credentials);

std::string updateMyCredentials(
    const std::string& strategy,
    const std::string& credentials,
    const kuzzleio::query_options& options);
```

## Arguments

| Arguments     | Type                                 | Description                              |
| ------------- | ------------------------------------ | ---------------------------------------- |
| `strategy`    | <pre>const std::string&</pre>        | Strategy to use                          |
| `credentials` | <pre>const std::string&</pre>        | JSON string representing the credentials |
| `options`     | <pre>kuzzleio::query_options\*</pre> | Query options                            |

### options

Additional query options

| Property   | Type<br/>(default)           | Description                                                                  |
| ---------- | ---------------------------- | ---------------------------------------------------------------------------- |
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle error](/sdk/cpp/1/essentials/error-handling).

## Return

A JSON string representing the updated credentials.

## Usage

<<< ./snippets/update-my-credentials.cpp

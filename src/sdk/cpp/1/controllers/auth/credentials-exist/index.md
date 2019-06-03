---
code: true
type: page
title: credentialsExist
description: Check that the current user has credentials for the specified strategy
---

# credentialsExist

Check that the current user has credentials for the specified strategy.

## Signature

```cpp
bool credentialsExist(const std::string& strategy);

bool credentialsExist(const std::string& strategy, const kuzzleio::query_options& options);
```

## Arguments

| Arguments  | Type                                 | Description     |
| ---------- | ------------------------------------ | --------------- |
| `strategy` | <pre>const std::string&</pre>        | Strategy to use |
| `options`  | <pre>kuzzleio::query_options\*</pre> | Query options   |

### options

Additional query options

| Property   | Type<br/>(default)           | Description                                                                  |
| ---------- | ---------------------------- | ---------------------------------------------------------------------------- |
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Return

A boolean indicating if credentials exists for the strategy.

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle error](/sdk/cpp/1/essentials/error-handling).

## Usage

<<< ./snippets/credentials-exist.cpp

---
code: true
type: page
title: getConfig
description: Returns the current Kuzzle configuration.
---

# getConfig

Returns the current Kuzzle configuration.

<div class="alert alert-warning">
  This route should only be accessible to administrators, as it might return sensitive information about the backend.
</div>

## Signature

```cpp
std::string getConfig();

std::string getConfig(const kuzzleio::query_options& options);
```

## Arguments

| Arguments | Type                                 | Description   |
| --------- | ------------------------------------ | ------------- |
| `options` | <pre>kuzzleio::query_options\*</pre> | Query options |

### options

Additional query options

| Option     | Type<br/>(default)           | Description                                                                  |
| ---------- | ---------------------------- | ---------------------------------------------------------------------------- |
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Return

A JSON string representing current server configuration.

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle error](/sdk/cpp/1/essentials/error-handling).

## Usage

<<< ./snippets/get-config.cpp

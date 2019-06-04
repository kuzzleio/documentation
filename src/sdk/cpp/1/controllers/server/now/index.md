---
code: true
type: page
title: now
description: Returns the current server timestamp, in Epoch-millis
---

# now

Fetch the current server timestamp, in Epoch-millis format.

## Signature

```cpp
long long now();

long long now(const kuzzleio::query_options& options);
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

An Epoch-millis timestamp.

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle error](/sdk/cpp/1/essentials/error-handling).

## Usage

<<< ./snippets/now.cpp

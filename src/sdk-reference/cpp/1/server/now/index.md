---
layout: sdk.html.hbs
title: now
description: Returns the current server timestamp, in Epoch-millis
---

# now

Fetch the current server timestamp, in Epoch-millis format.

## Signature

```cpp
long long now(kuzzleio::query_options *options = nullptr)
```

## Arguments

| Arguments | Type          | Description       |
| --------- | ------------- | ------------------|
| `options` | <pre>kuzzleio::query_options\*</pre> | Query options |

### options

Additional query options

| Option     | Type<br/>(default)   | Description  |
| ---------- | ------- | -------------- |
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Return

An Epoch-millis timestamp.

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/cpp/1/error-handling).

## Usage

[snippet=now]

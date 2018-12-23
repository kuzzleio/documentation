---
layout: sdk.html.hbs
title: now
description: Returns the current server timestamp, in Epoch-millis
---

# now

{{{since "1.0.0"}}}

Fetch the current server timestamp, in Epoch-millis format.

## Signature

```cpp
long long now(kuzzleio::query_options *options = nullptr)
```

## Arguments

| Arguments | Type          | Description       |
| --------- | ------------- | ------------------|
| `options` | kuzzleio::query_options* | Query options | no       |

### options

Additional query options

| Option     | Type   | Description                       | Default |
| ---------- | ------- | --------------------------------- | 
| `queuable` | bool | If true, queues the request during downtime, until connected to Kuzzle again | `true`  |

## Return

Returns Epoch-millis timestamp as `long long`.

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/cpp/1/essentials/error-handling).

## Usage

[snippet=now]

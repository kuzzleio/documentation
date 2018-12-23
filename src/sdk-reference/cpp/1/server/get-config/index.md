---
layout: sdk.html.hbs
title: getConfig
description: Returns the current Kuzzle configuration.
---

# getConfig

{{{since "1.0.0"}}}

Returns the current Kuzzle configuration.

<div class="alert alert-warning">
  This route should only be accessible to administrators, as it might return sensitive information about the backend.
</div>

## Signature

```cpp
std::string getConfig(kuzzleio::query_options* options=nullptr);
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

Returns a JSON string representing current server configuration.

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/cpp/1/essentials/error-handling).

## Usage

[snippet=get-config]

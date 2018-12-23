---
layout: sdk.html.hbs
title: list
description: List the indexes
---

# list

Get the complete list of data indexes handled by Kuzzle.

## Signature

```cpp
std::vector<std::string> list(kuzzleio::query_options *options = null)
```

## Arguments

| Arguments | Type          | Description       |
| --------- | ------------- | ------------------|
| `options` | <pre>kuzzleio::query_options*</pre> | Query options |

### options

Additional query options

| Option     | Type    | Description                       | 
| ---------- | ------- | --------------------------------- | 
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Return

A vector of string containing the list of indexes present in Kuzzle

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/cpp/1/essentials/error-handling).

## Usage

[snippet=list]

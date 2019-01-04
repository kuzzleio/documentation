---
layout: sdk.html.hbs
title: exists
description: Check for index existence
---

# exists

Checks if the given index exists in Kuzzle.

## Signature

```cpp
bool exists(const std::string& index);

bool exists(const std::string& index, const kuzzleio::query_options& options);
```

## Arguments

| Arguments | Type          | Description       |
| --------- | ------------- | ------------------|
| `index`   | <pre>const std::string&</pre>   | Index name     |
| `options` | <pre>kuzzleio::query_options\*</pre> | Query options |

### options

Additional query options

| Option     | Type<br/>(default)   | Description  |   
| ---------- | ------- | --------------------------------- | 
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Return

A boolean indicating whether the index exists or not.

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/cpp/1/error-handling).

## Usage

[snippet=exists]

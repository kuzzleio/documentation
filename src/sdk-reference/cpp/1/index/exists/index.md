---
layout: sdk.html.hbs
title: exists
description: Check for index existence
---

# Exists

Checks if the given index exists in Kuzzle.

## Signature

```cpp
bool exists(const std::string& index, kuzzleio::query_options *options = null)
```

## Arguments

| Arguments | Type          | Description                                             | Required |
| --------- | ------------- | ------------------------------------------------------- | -------- |
| `index`   | const std::string&   | Index name                                              | yes      |
| `options` | kuzzleio::query_options* | A pointer to a `kuzzleio::query_options` containing query options | no       |

### options

Additional query options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | 
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Return

Returns a `boolean` that indicate whether the index exists or not.

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/cpp/1/essentials/error-handling).

## Usage

[snippet=exists]

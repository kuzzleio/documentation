---
layout: sdk.html.hbs
algolia: true
title: list
description: List the indexes
order: 400
---

# List

Get the complete list of data indexes handled by Kuzzle.

## Signature

```cpp
std::vector<std::string> list(kuzzleio::query_options *options = null)
```

## Arguments

| Arguments | Type          | Description                                             | Required |
| --------- | ------------- | ------------------------------------------------------- | -------- |
| `options` | kuzzleio::query_options* | A pointer to a `query_options` containing query options | no       |

### **Options**

Additional query options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Return

Returns a `std::vector<std::string>` containing the list of indexes names present in Kuzzle

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/cpp/1/essentials/error-handling).

## Usage

[snippet=list]

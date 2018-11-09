---
layout: sdk.html.hbs
algolia: true
title: exists
description: Check if collection exists
---

# exists

Check if a collection exists in Kuzzle.

## Signature

```cpp
bool exists(const std::string& index, const std::string& collection, kuzzleio::query_options *options=nullptr)
```

## Arguments

| Arguments    | Type    | Description | Required
|--------------|---------|-------------|----------
| ``index`` | const std::string& | Index name    | yes  |
| ``collection`` | const std::string& | Collection name    | yes  |
| ``options`` | kuzzleio::query_options* | Query options    | no  |

### **options**

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Return

True if the collection exists

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/cpp/1/essentials/error-handling).

## Usage

[snippet=exists]

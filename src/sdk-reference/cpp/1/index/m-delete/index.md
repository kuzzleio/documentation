---
layout: sdk.html.hbs
algolia: true
title: mDelete
description: Deletes multiple indexes
---


# mDelete

Deletes multiple indexes at once.

## Signature

```cpp
std::vector<std::string> mDelete(std::vector<std::string> indexes, kuzzleio::query_options *options = null)
```

## Arguments

| Arguments | Type                       | Description                                             | Required |
| --------- | -------------------------- | ------------------------------------------------------- | -------- |
| `indexes` | `std::vector<std::string>` | containing list of indexes names                        | yes      |
| `options` | kuzzleio::query_options              | A pointer to a `kuzzleio::query_options` containing query options | no       |

### **Options**

Additional query options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Return

Returns a `std::vector<std::string>` containing the list of indexes names deleted

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/cpp/1/essentials/error-handling).

## Usage

[snippet=mDelete]

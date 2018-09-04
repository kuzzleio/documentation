---
layout: sdk.html
algolia: true
title: mDelete
description: Deletes multiple indexes
order: 600
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
| `options` | query_options              | A pointer to a `query_options` containing query options | no       |

### **Options**

Additional query options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Return

Returns a `std::vector<std::string>` containing the list of indexes names deleted

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/essentials/error-handling).

## Usage

[code-example=mDelete]

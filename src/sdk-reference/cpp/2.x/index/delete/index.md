---
layout: sdk.html
algolia: true
title: delete
description: Deletes an index
order: 500
---

# Delete

Deletes an entire data index from Kuzzle.

## Signature

```cpp
void delete_(std::string index, kuzzleio::query_options *options = null)
```

## Arguments

| Arguments | Type          | Description                                             | Required |
| --------- | ------------- | ------------------------------------------------------- | -------- |
| `index`   | std::string   | Index name                                              | yes      |
| `options` | query_options | A pointer to a `query_options` containing query options | no       |

### **Options**

Additional query options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/essentials/error-handling).

## Usage

[code-example=delete]

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

```java
void delete(String index, QueryOptions options)
void delete(String index)
```

## Arguments

| Arguments | Type         | Description       | Required |
| --------- | ------------ | ----------------- | -------- |
| `index`   | String       | Index name        | yes      |
| `options` | QueryOptions | The query options | no       |

### **Options**

Additional query options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/essentials/error-handling).

## Usage

[snippet=delete]

---
layout: sdk.html.hbs
algolia: true
title: adminExists
description: Checks that an administrator account exists.
order: 200
---

# adminExists

{{{since "1.0.0"}}}

Checks that an administrator account exists.

## Signature
```cpp
bool adminExists(query_options *options=nullptr);
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

Returns a `boolean`: `true` if it exists and `false` if it does not.

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/cpp/1/essentials/error-handling).

## Usage

[snippet=admin-exists]

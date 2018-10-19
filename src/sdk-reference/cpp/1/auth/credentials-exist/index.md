---
layout: sdk.html.hbs
algolia: true
title: credentialsExist
description: Check that the current user has credentials for the specified strategy
order: 200
---

# credentialsExist

Check that the current user has credentials for the specified strategy.

## Signature

```cpp
 bool credentialsExist(const std::string& strategy, query_options *options=nullptr);
```

## Arguments

| Arguments  | Type             | Description                                             | Required |
| ---------- | ---------------- | ------------------------------------------------------- | -------- |
| `strategy` | std::string      | Strategy to use                                         | yes      |
| `options`  | query_options\*    | A pointer to a `query_options` containing query options | no       |

### **Options**

Additional query options

| Property     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |


## Return

True if exists, false if not.

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/cpp/1/essentials/error-handling).

## Usage

[snippet=credentials-exist]

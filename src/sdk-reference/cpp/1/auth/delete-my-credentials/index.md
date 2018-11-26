---
layout: sdk.html.hbs
algolia: true
title: deleteMyCredentials
description: Delete the current user's credentials for the specified strategy
algolia: true
---

# deleteMyCredentials

Delete the current user's credentials for the specified `<strategy>`. If the credentials that generated the current JWT are removed, the user will remain logged in until he logs out or his session expires, after that they will no longer be able to log in with the deleted credentials.

## Signature

```cpp
void deleteMyCredentials(const std::string& strategy, query_options *options=nullptr);
```

## Arguments

| Arguments    | Type    | Description | Required
|--------------|---------|-------------|----------
| `strategy` | const std::string& | the strategy to use    | yes
| `options`  | query_options\*    | A pointer to a `kuzzleio::query_options` containing query options | no       |


### **Options**

Additional query options

| Property     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/cpp/1/essentials/error-handling).

## Usage

[snippet=delete-my-credentials]

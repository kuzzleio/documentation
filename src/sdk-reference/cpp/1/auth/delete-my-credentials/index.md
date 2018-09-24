---
layout: sdk.html
algolia: true
title: deleteMyCredentials
description: Delete the current user's credentials for the specified strategy
order: 200
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
| `options`  | query_options    | A pointer to a `query_options` containing query options | no       |


### **Options**

Additional query options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Return

## Usage

[snippet=delete-my-credentials]

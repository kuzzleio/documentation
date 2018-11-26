---
layout: sdk.html.hbs
algolia: true
title: createMyCredentials
description: Create the current user's credentials for the specified `<strategy>`.
algolia: true
---

# createMyCredentials

Create the current user's credentials for the specified `<strategy>`.

## Signature

```cpp
std::string createMyCredentials(const std::string& strategy, const std::string& credentials, query_options* options=nullptr);
```

## Arguments

| Arguments    | Type    | Description
|--------------|---------|-------------
| `strategy` | const std::string& | the strategy to use
| `credentials` | const std::string& | the new credentials
| `options`  | query_options*    | A pointer to a `kuzzleio::query_options` containing query options


### **Options**

Additional query options

| Property     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |


## Return

A string representing a JSON object of the new credentials.

## Usage

[snippet=create-my-credentials]

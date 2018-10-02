---
layout: sdk.html
algolia: true
title: getMyCredentials
description: Returns the current user's credential information for the specified `<strategy>`.
order: 200
---

# getMyCredentials

Returns the current user's credential information for the specified `<strategy>`. The data returned will depend on the specified strategy. The result can be an empty object.

## Signature

```cpp
std::string getMyCredentials(const std::string& strategy, query_options *options=nullptr);
```

## Arguments

| Arguments    | Type    | Description | Required
|--------------|---------|-------------|----------
| `strategy` | const std::string& | the strategy to use    | yes
| `options`  | query_options*    | A pointer to a `query_options` containing query options | no       |

### **Options**

Additional query options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | bool | Make this request queuable or not | `true`  |

## Return

Returns a string representing a JSON with the credentials for the provided authentication strategy.

## Usage

[snippet=get-my-credentials]

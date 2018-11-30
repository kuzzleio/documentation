---
layout: sdk.html.hbs
title: updateMyCredentials
description: Update the current user's credentials for the specified `<strategy>`.
---

# updateMyCredentials

Update the current user's credentials for the specified `<strategy>`. The credentials to send will depend on the authentication plugin and the authentication strategy.

## Signature

```cpp
std::string updateMyCredentials(const std::string& strategy, const std::string& credentials, query_options *options=nullptr);
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


## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/cpp/1/essentials/error-handling).

## Return

A string representing a JSON object of the new credentials.

## Usage

[snippet=update-my-credentials]

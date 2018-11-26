---
layout: sdk.html.hbs
algolia: true
title: updateSelf
description: Updates the current user object in Kuzzle.
algolia: true
---

# updateSelf

Updates the current user object in Kuzzle.

## Signature

```cpp
kuzzle_user* updateSelf(const std::string& content, query_options* options=nullptr);
```

## Arguments

| Arguments    | Type    | Description
|--------------|---------|-------------
| `content` | const std::string& | the new credentials
| `options`  | query_options*    | A pointer to a `kuzzleio::query_options` containing query options


### **Options**

Additional query options

| Property     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |


## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/cpp/1/essentials/error-handling).


## Return

A pointer to a kuzzle_user.

## Usage

[snippet=update-self]

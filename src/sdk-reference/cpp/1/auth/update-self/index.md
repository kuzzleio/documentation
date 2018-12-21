---
layout: sdk.html.hbs
title: updateSelf
description: Updates the current user object in Kuzzle.
---

# updateSelf

Updates the current user object in Kuzzle.

## Arguments

```cpp
kuzzleio::User updateSelf(const std::string& content, query_options* options=nullptr);
```

| Arguments    | Type    | Description
|--------------|---------|-------------|
| `content` | <pre>const std::string&</pre> | JSON string representing the user content |
| `options`  | <pre>kuzzleio::query_options*</pre>  | Query options |


### **Options**

Additional query options:

| Property     | Type    | Description  |
| ---------- | ------- | -------------- |
| `queuable` | <pre>bool (true)</pre> | If true, queues the request during downtime, until connected to Kuzzle again |

## Return

A [kuzzleio::User]({{ site_base_path }}sdk-reference/cpp/1/user/) object.

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/cpp/1/essentials/error-handling).

## Usage

[snippet=update-self]

---
layout: sdk.html.hbs
title: updateSelf
description: Updates the current user object in Kuzzle.
---

# updateSelf

Updates the current user object in Kuzzle.

## Arguments

```cpp
User updateSelf(const std::string& content, query_options* options=nullptr);
```

| Arguments    | Type    | Description
|--------------|---------|-------------|
| `content` | <pre>const std::string&</pre> | New credentials |
| `options`  | <pre>kuzzleio::query_options*</pre>  | Optional query options |


### **Options**

Additional query options:

| Property     | Type    | Description  |
| ---------- | ------- | -------------- |
| `queuable` | <pre>bool (true)</pre> | If true, queues the request during downtime, until connected to Kuzzle again |

## Return

A [User]({{ site_base_path }}sdk-reference/cpp/1/user/) object.

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/cpp/1/essentials/error-handling).

## Usage

[snippet=update-self]

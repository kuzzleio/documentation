---
layout: sdk.html.hbs
algolia: true
title: now
description: Returns the current server timestamp, in Epoch-millis
order: 200
---

# now

{{{since "1.0.0"}}}

Fetch the current server timestamp, in Epoch-millis format.

## Signature

```cpp
long long now(kuzzleio::query_options *options = nullptr)
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

Returns Epoch-millis timestamp as `long long`.

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/cpp/1/essentials/error-handling).

## Usage

[snippet=now]

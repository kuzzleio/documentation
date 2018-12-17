---
layout: sdk.html.hbs
title: list
description: List the indexes
---

# List

Get the complete list of data indexes handled by Kuzzle.

## Signature

```csharp
public SWIGTYPE_p_std__vectorT_std__string_t list();
public SWIGTYPE_p_std__vectorT_std__string_t list(QueryOptions options);
```

## Arguments

| Arguments | Type          | Description                                             | Required |
| --------- | ------------- | ------------------------------------------------------- | -------- |
| `options` | Kuzzleio::QueryOptions | A `Kuzzleio::QueryOptions` containing query options | no       |

### **Options**

Additional query options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | bool | Make this request queuable or not | `true`  |

## Return

Returns a `List<string>` containing the list of indexes names present in Kuzzle

## Exceptions

Throws a `Kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/csharp/1/essentials/error-handling).

## Usage

[snippet=list]

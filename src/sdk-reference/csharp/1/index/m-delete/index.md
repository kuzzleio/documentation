---
layout: sdk.html.hbs
title: mDelete
description: Deletes multiple indexes
---

# mDelete

Deletes multiple indexes at once.

## Signature

```csharp
public SWIGTYPE_p_std__vectorT_std__string_t mDelete(SWIGTYPE_p_std__vectorT_std__string_t indexes);
public SWIGTYPE_p_std__vectorT_std__string_t mDelete(SWIGTYPE_p_std__vectorT_std__string_t indexes, QueryOptions options);
```

## Arguments

| Arguments | Type                       | Description                                             | Required |
| --------- | -------------------------- | ------------------------------------------------------- | -------- |
| `indexes` | `List<string>` | containing list of indexes names                        | yes      |
| `options` | Kuzzleio::QueryOptions              | A `Kuzzleio::QueryOptions` containing query options | no       |

### **Options**

Additional query options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | bool | Make this request queuable or not | `true`  |

## Return

Returns a `List<string>` containing the list of indexes names deleted

## Exceptions

Throws a `Kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/csharp/1/essentials/error-handling).

## Usage

[snippet=mDelete]

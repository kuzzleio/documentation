---
layout: sdk.html.hbs
title: exists
description: Check for index existence
---

# Exists

Checks if the given index exists in Kuzzle.

## Signature

```csharp
public bool exists(string index);
public bool exists(string index, QueryOptions options);
```

## Arguments

| Arguments | Type          | Description                                             | Required |
| --------- | ------------- | ------------------------------------------------------- | -------- |
| `index`   | string   | Index name                                              | yes      |
| `options` | Kuzzleio::QueryOptions | A `Kuzzleio::QueryOptions` containing query options | no       |

### **Options**

Additional query options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | bool | Make this request queuable or not | `true`  |

## Return

Returns a `bool` that indicate whether the index exists or not.

## Exceptions

Throws a `Kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/csharp/1/essentials/error-handling).

## Usage

[snippet=exists]

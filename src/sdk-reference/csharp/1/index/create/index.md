---
layout: sdk.html.hbs
title: create
description: Create an index
---

# create

Create a new index in Kuzzle

## Signature

```csharp
public void create(string index);
public void create(string index, QueryOptions options);
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

## Exceptions

Throws a `Kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/csharp/1/essentials/error-handling).

## Usage

[snippet=create]

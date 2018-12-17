---
layout: sdk.html.hbs
title: getStrategies
description: Get all authentication strategies registered in Kuzzle.
---

# getStrategies

Get all authentication strategies registered in Kuzzle.

## Signature

```csharp
public SWIGTYPE_p_std__vectorT_std__string_t getStrategies();
public SWIGTYPE_p_std__vectorT_std__string_t getStrategies(QueryOptions options);
```

## Arguments

| Arguments    | Type    | Description
|--------------|---------|-------------
| `options`  | query_options*    | A `Kuzzleio::QueryOptions` containing query options

### **Options**

Additional query options

| Property     | Type    | Description                       | Default
| ---------- | ------- | --------------------------------- | -------
| `queuable` | bool | Make this request queuable or not | `true`

## Return

A vector of string.

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/csharp/1/essentials/error-handling).

## Usage

[snippet=get-strategies]

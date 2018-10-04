---
layout: sdk.html
algolia: true
title: getStrategies
description: Get all authentication strategies registered in Kuzzle.
order: 200
---

# getStrategies

Get all authentication strategies registered in Kuzzle.

## Signature

```cpp
std::vector<std::string> getStrategies(query_options *options=nullptr);
```

## Arguments

| Arguments    | Type    | Description
|--------------|---------|-------------
| `options`  | query_options*    | A pointer to a `query_options` containing query options

### **Options**

Additional query options

| Option     | Type    | Description                       | Default
| ---------- | ------- | --------------------------------- | -------
| `queuable` | bool | Make this request queuable or not | `true`

## Return

A vector of string.

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/essentials/error-handling).

## Usage

[snippet=get-strategies]

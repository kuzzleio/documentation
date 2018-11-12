---
layout: sdk.html.hbs
algolia: true
title: getStrategies
description: Get all authentication strategies registered in Kuzzle.
---

# getStrategies

Get all authentication strategies registered in Kuzzle.

## Signature

```java
public io.kuzzle.sdk.StringVector getStrategies(
  io.kuzzle.sdk.QueryOptions
);
public io.kuzzle.sdk.StringVector getStrategies();
```

## Arguments

| Arguments    | Type    | Description
|--------------|---------|-------------
| `options`  | io.kuzzle.sdk.QueryOptions    | An object containing Query options

### **Options**

Additional query options

| Property     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Return

A StringVector object.

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/java/1/essentials/error-handling).

## Usage

[snippet=get-strategies]

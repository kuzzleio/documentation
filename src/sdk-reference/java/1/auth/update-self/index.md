---
layout: sdk.html.hbs
algolia: true
title: updateSelf
description: Updates the current user object in Kuzzle.
order: 200
---

# updateSelf

Updates the current user object in Kuzzle.

## Signature

```java
public io.kuzzle.sdk.User updateSelf(
  java.lang.String, 
  io.kuzzle.sdk.QueryOptions
);
public io.kuzzle.sdk.User updateSelf(
  java.lang.String
);
```

## Arguments

| Arguments    | Type    | Description
|--------------|---------|-------------
| `content` | java.lang.String | the new credentials
| `options`  | io.kuzzle.sdk.QueryOptions    | An object containing query options.


###### **Options**

Additional query options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |


## Return

A User object.

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/essentials/error-handling).

## Usage

[snippet=update-self]

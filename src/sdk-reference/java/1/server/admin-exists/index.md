---
layout: sdk.html.hbs
algolia: true
title: adminExists
description: Checks that an administrator account exists.
order: 200
---

# adminExists

{{{since "1.0.0"}}}

Checks that an administrator account exists.

## Signature

```java
public boolean adminExists(
  io.kuzzle.sdk.QueryOptions
);
public boolean adminExists();
```

## Arguments

| Arguments | Type   | Description                         | Required |
| --------- | ------ | ----------------------------------- | -------- |
| `options` | io.kuzzle.sdk.QueryOptions | An object containing query options. | no       |

### **Options**

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |


## Return

Returns a `boolean` true if it exists and false if it does not.

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/essentials/error-handling).

## Usage

[snippet=admin-exists]

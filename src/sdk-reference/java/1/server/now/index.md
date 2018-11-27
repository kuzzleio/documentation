---
layout: sdk.html.hbs
algolia: true
title: now
description: Returns the current server timestamp, in Epoch-millis
algolia: true
---

# now

{{{since "1.0.0"}}}

Returns the current server timestamp, in Epoch-millis format.

## Arguments

```java
public java.util.Date now(
io.kuzzle.sdk.QueryOptions
);
public java.util.Date now();
```

| Arguments | Type   | Description                         | Required |
| --------- | ------ | ----------------------------------- | -------- |
| `options` | io.kuzzle.sdk.QueryOptions | An object containing query options. | no       |

### **Options**

Additional query options

| Property   | Type  | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Return

Returns current server timestamp as `java.Util.Date`.

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/java/1/essentials/error-handling).

## Usage

[snippet=now]

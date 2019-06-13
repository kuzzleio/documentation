---
code: true
type: page
title: now
description: Returns the current server timestamp, in Epoch-millis
---

# now



Returns the current server timestamp, in Epoch-millis format.

## Arguments

```java
public java.util.Date now(
io.kuzzle.sdk.QueryOptions
);
public java.util.Date now();
```

| Arguments | Type                       | Description                         | Required |
| --------- | -------------------------- | ----------------------------------- | -------- |
| `options` | io.kuzzle.sdk.QueryOptions | An object containing query options. | no       |

### **Options**

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Return

Returns current server timestamp as `java.Util.Date`.

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle error](/sdk/java/1/essentials/error-handling/).

## Usage

<<< ./snippets/now.java

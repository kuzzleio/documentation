---
code: true
type: page
title: adminExists
description: Checks that an administrator account exists.
---

# adminExists



Checks that an administrator account exists.

## Arguments

```java
public boolean adminExists(
io.kuzzle.sdk.QueryOptions
);
public boolean adminExists();
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

Returns a `boolean` set to `true` if an admin exists and `false` if it does not.

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle error](/sdk/java/1/essentials/error-handling/).

## Usage

<<< ./snippets/admin-exists.java

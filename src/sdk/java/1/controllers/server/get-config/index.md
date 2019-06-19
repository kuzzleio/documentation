---
code: true
type: page
title: getConfig
description: Returns the current Kuzzle configuration.
---

# getConfig



Returns the current Kuzzle configuration.

:::warning
This route should only be accessible to administrators, as it might return sensitive information about the backend.
:::

## Arguments

```java
public java.lang.String getConfig(
io.kuzzle.sdk.QueryOptions
);
public java.lang.String getConfig();
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

Return server configuration as a `String`.

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle error](/sdk/java/1/essentials/error-handling/).

## Usage

<<< ./snippets/get-config.java

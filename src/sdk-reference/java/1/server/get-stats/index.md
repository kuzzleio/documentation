---
layout: sdk.html.hbs
algolia: true
title: getStats
description: Returns statistics snapshots within a provided timestamp range.
---


# getStats
{{{since "1.0.0"}}}

Returns statistics snapshots within a provided timestamp range.
By default, snapshots are made every 10 seconds and they are stored for 1 hour.

These statistics include:

* the number of connected users per protocol (not available for all protocols)
* the number of ongoing requests
* the number of completed requests since the last frame
* the number of failed requests since the last f

## Arguments

```java
public java.lang.String getStats(
long,
long,
io.kuzzle.sdk.QueryOptions
);
public java.lang.String getStats(
long,
long
);
```

| Arguments | Type   | Description                         | Required |
| --------- | ------ | ----------------------------------- | -------- |
| `startTime` | long | begining of statistics frame set (timestamp or datetime format) | yes       |
| `stopTime`  | long | end of statistics frame set (timestamp or datetime format)      | yes       |
| `options`   | io.kuzzle.sdk.QueryOptions | An object containing query options. | no       |

### **Options**

Additional query options

| Property   | Type  | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Return

Return statistics snapshots within a provided timestamp range as a `String`.

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/java/1/essentials/error-handling).

## Usage

[snippet=get-stats]

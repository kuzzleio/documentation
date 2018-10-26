---
layout: sdk.html.hbs
algolia: true
title: count
description: Count subscribers for a subscription room
order: 200
---

# count

Returns the number of other connections sharing the same subscription.

## Signature

```java
public int count(String roomId)
public int count(String roomId, io.kuzzle.sdk.QueryOptions options)
```

## Arguments

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| ``roomId`` | String | Subscription room ID |
| `options` | io.kuzzle.sdk.QueryOptions | QueryOptions |

###### **options**

Additional query options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Return

Returns the number of active connections using the same provided subscription room.

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/essentials/error-handling).

## Usage

[snippet=count]

---
code: true
type: page
title: count
description: Count subscribers for a subscription room
---

# count

Returns the number of other connections sharing the same subscription.

## Arguments

```java
public int count(String roomId)
public int count(String roomId, io.kuzzle.sdk.QueryOptions options)
```

<br/>

| Arguments | Type                                  | Description          |
| --------- | ------------------------------------- | -------------------- |
| `roomId`  | <pre>String</pre>                     | Subscription room ID |
| `options` | <pre>io.kuzzle.sdk.QueryOptions</pre> | QueryOptions         |

### options

Additional query options

| Option     | Type<br/>(default)              | Description                       |
| ---------- | ------------------------------- | --------------------------------- |
| `queuable` | <pre>boolean</pre><br/>(`true`) | Make this request queuable or not |

## Return

Returns the number of active connections using the same provided subscription room.

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle error](/sdk/java/1/essentials/error-handling/).

## Usage

<<< ./snippets/count.java

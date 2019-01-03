---
layout: sdk.html.hbs
title: unsubscribe
description: Removes a subscription
---

# unsubscribe

Removes a subscription.

## Arguments

```java
void unsubscribe(String roomId)
void unsubscribe(String roomId, io.kuzzle.sdk.QueryOptions options)
```

<br/>

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| `roomId` | <pre>String</pre> | Subscription room ID  |
| `options` | <pre>io.kuzzle.sdk.QueryOptions</pre> | Query options |

### options

Additional query options

| Option     | Type<br/>(default)    | Description                       |
| ---------- | ------- | --------------------------------- |
| `queuable` | <pre>boolean</pre><br/>(`true`) | Make this request queuable or not |

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/java/1/error-handling).

## Usage

[snippet=unsubscribe]

---
layout: sdk.html.hbs
algolia: true
title: unsubscribe
description: Removes a subscription
order: 200
---

# unsubscribe

Removes a subscription.

## Signature

```java
void unsubscribe(String roomId)
void unsubscribe(String roomId, io.kuzzle.sdk.QueryOptions options)
```

## Arguments

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| ``roomId`` | String | Subscription room ID  |
| `options` | io.kuzzle.sdk.QueryOptions | Query options |

### **options**

Additional query options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/java/1/essentials/error-handling).

## Usage

[snippet=unsubscribe]

---
layout: sdk.html.hbs
algolia: true
title: publish
description:
order: 200
---

# publish

Sends a real-time `<message>` to Kuzzle. The `<message>` will be dispatched to all clients with subscriptions matching the `<index>`, the `<collection>` and the `<message>` content.  

The `<index>` and `<collection>` are indicative and serve only to distinguish the rooms. They are not required to exist in the database

**Note:** real-time messages are not persisted in the database.

## Signature

```java
public void publish(String index, String collection, String message)
public void publish(String index, String collection, String message, io.kuzzle.sdk.QueryOptions options)
```

## Arguments

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| ``index`` | String | Index name    |
| ``collection`` | String | Collection name   |
| ``message`` | String | JSON string representing the message to send |
| `options` | io.kuzzle.sdk.QueryOptions | Query options |

### **options**

Additional query options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/java/1/essentials/error-handling).

## Usage

[snippet=publish]

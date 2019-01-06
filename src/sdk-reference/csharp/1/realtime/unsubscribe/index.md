---
layout: sdk.html.hbs
title: unsubscribe
description: Removes a subscription
---

# unsubscribe

Removes a subscription.

## Signature

```csharp
public void unsubscribe(string room_id);

public void unsubscribe(string room_id, query_options options);

```

## Arguments

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| `room_id` | <pre>string</pre> | Subscription room ID  |
| `options` | <pre>Kuzzleio::QueryOptions\*</pre> | Query options |

### options

Additional query options

| Option     | Type<br/>(default)  | Description   |
| ---------- | ------- | --------------------------------- |
| `queuable` | <pre>bool</pre><br/>(`true`) |  If true, queues the request during downtime, until connected to Kuzzle again |

## Exceptions

Throws a `Kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/csharp/1/error-handling).

## Usage

[snippet=unsubscribe]

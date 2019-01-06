---
layout: sdk.html.hbs
title: count
description: Count subscribers for a subscription room
---

# count

Returns the number of other connections sharing the same subscription.

## Signature

```csharp
public int count(string room_id);

public int count(string room_id, query_options options);

```

## Arguments

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| `room_id` | <pre>string</pre> | Subscription room ID |
| `options` | <pre>Kuzzleio::QueryOptions</pre> | Query options |

### options

Additional query options

| Option     | Type<br/>(default)  | Description   |
| ---------- | ------- | --------------------------------- |
| `queuable` | <pre>bool</pre><br/>(`true`) |  If true, queues the request during downtime, until connected to Kuzzle again |

## Return

Returns the number of active connections using the same provided subscription room.

## Exceptions

Throws a `Kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/csharp/1/error-handling).

## Usage

[snippet=count]

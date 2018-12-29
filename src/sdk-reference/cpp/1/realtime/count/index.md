---
layout: sdk.html.hbs
title: count
description: Count subscribers for a subscription room
---

# count

Returns the number of other connections sharing the same subscription.

## Signature

```cpp
int count(const std::string& room_id)

int count(const std::string& room_id, const kuzzleio::query_options& options)
```

## Arguments

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| `room_id` | <pre>const std::string&</pre> | Subscription room ID |
| `options` | <pre>kuzzleio::query_options\*</pre> | Query options |

### options

Additional query options

| Option     | Type<br/>(default)  | Description   |
| ---------- | ------- | --------------------------------- |
| `queuable` | <pre>bool</pre><br/>(`true`) |  If true, queues the request during downtime, until connected to Kuzzle again |

## Return

Returns the number of active connections using the same provided subscription room.

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/cpp/1/essentials/error-handling).

## Usage

[snippet=count]

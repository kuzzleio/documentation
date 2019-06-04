---
code: true
type: page
title: unsubscribe
description: Removes a subscription
---

# unsubscribe

Removes a subscription.

## Signature

```cpp
void unsubscribe(const std::string& room_id);

void unsubscribe(const std::string& room_id, const kuzzleio::query_options& options);
```

## Arguments

| Arguments | Type                                 | Description          |
| --------- | ------------------------------------ | -------------------- |
| `room_id` | <pre>const std::string&</pre>        | Subscription room ID |
| `options` | <pre>kuzzleio::query_options\*</pre> | Query options        |

### options

Additional query options

| Option     | Type<br/>(default)           | Description                                                                  |
| ---------- | ---------------------------- | ---------------------------------------------------------------------------- |
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle error](/sdk/cpp/1/essentials/error-handling).

## Usage

<<< ./snippets/unsubscribe.cpp

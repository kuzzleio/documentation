---
layout: sdk.html.hbs
title: count
description: Count subscribers for a subscription room
---

# count

Returns the number of other connections sharing the same subscription.

## Arguments

```cpp
int count(const std::string& room_id, kuzzleio::query_options *options=nullptr)
```

<br/>

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| `room_id` | <pre>const std::string&</pre> | Subscription room ID |
| `options` | <pre>kuzzleio::query_options*</pre> | A pointer to a `query_options` containing query options |

### options

Additional query options

| Option     | Type<br/>(default)  | Description   |
| ---------- | ------- | --------------------------------- |
| `queuable` | <pre>bool</pre><br/>(`true`) | Make this request queuable or not |

## Return

Returns the number of active connections using the same provided subscription room.

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/cpp/1/essentials/error-handling).

## Usage

[snippet=count]

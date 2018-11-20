---
layout: sdk.html.hbs
algolia: true
title: unsubscribe
description: Removes a subscription
---


# unsubscribe

Removes a subscription.

## Arguments

```cpp
void unsubscribe(const std::string& room_id, kuzzleio::query_options *options=nullptr)
```

<br/>

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| `room_id` | <pre>const std::string&</pre> | Subscription room ID  |
| `options` | <pre>kuzzleio::query_options*</pre> | Query options |

### options

Additional query options

| Option     | Type<br/>(default)  | Description   |
| ---------- | ------- | --------------------------------- |
| `queuable` | <pre>boolean</pre><br/>(`true`) | Make this request queuable or not |

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/cpp/1/essentials/error-handling).

## Usage

[snippet=unsubscribe]

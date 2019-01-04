---
layout: sdk.html.hbs
title: publish
description: Publish a real-time message
---

# publish

Sends a real-time message to Kuzzle. The message will be dispatched to all clients with subscriptions matching the index, the collection and the message content.

The index and collection are indicative and serve only to distinguish the rooms. They are not required to exist in the database

**Note:** real-time messages are not persisted in the database.

## Signature

```cpp
void publish(
    const std::string& index, 
    const std::string& collection, 
    const std::string& message);

void publish(
    const std::string& index, 
    const std::string& collection, 
    const std::string& message, 
    const kuzzleio::query_options& options);
```

## Arguments

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| `index` | <pre>const std::string&</pre> | Index name    |
| `collection` | <pre>const std::string&</pre> | Collection name    |
| `message` | <pre>const std::string&</pre> | JSON string representing a JSON payload |
| `options` | <pre>kuzzleio::query_options\*</pre> | Query options |

### options

Additional query options

| Option     | Type<br/>(default)  | Description   |
| ---------- | ------- | --------------------------------- |
| `queuable` | <pre>bool</pre><br/>(`true`) |  If true, queues the request during downtime, until connected to Kuzzle again |
| `volatiles` | <pre>const char\*</pre><br/>(`"{}"`) | JSON string representing subscription information, used in [user join/leave notifications]({{site_base_path}}api/1/essentials/volatile-data/) |

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/cpp/1/error-handling).

## Usage

[snippet=publish]

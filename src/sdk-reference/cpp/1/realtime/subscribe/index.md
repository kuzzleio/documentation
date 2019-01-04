---
layout: sdk.html.hbs
title: subscribe
description: Subscribe to real-time notifications
---

# subscribe

Subscribes by providing a set of filters: messages, document changes and, optionally, user events matching the provided filters will generate [real-time notifications]({{site_base_path}}api/1/essentials/real-time), sent to you in real-time by Kuzzle.

## Signature

```cpp
std::string subscribe(
    const std::string& index, 
    const std::string& collection, 
    const std::string& filters, 
    kuzzleio::NotificationListener* listener);

std::string subscribe(
    const std::string& index, 
    const std::string& collection, 
    const std::string& filters, 
    kuzzleio::NotificationListener* listener, 
    const kuzzleio::room_options& options);
```

## Arguments

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| `index` | <pre>const std::string&</pre> | Index name    |
| `collection` | <pre>const std::string&</pre> | Collection name    |
| `filters` | <pre>const std::string&</pre> | JSON string representing a set of filters following [Koncorde syntax]({{site_base_path}}koncorde/1/terms/) |
| `listener` | <pre>kuzzleio::NotificationListener*</pre> | Listener function to handle notifications |
| `options` | <pre>kuzzleio::room_options*</pre> | Subscription options |

### listener

Listener function that will be called each time a new notifications is received.
The listener will receive a [const kuzzleio::notification_result*]({{site_base_path}}sdk-reference/cpp/1/realtime-notifications) as only argument.

### options

Additional subscription options.

| Property   | Type<br/>(default)    | Description                       |
| ---------- | ------- | --------------------------------- |
| `scope` | <pre>const char\*</pre><br/>(`all`) | Subscribe to document entering or leaving the scope<br/>Possible values: `all`, `in`, `out`, `none` |
| `users` | <pre>const char\*</pre><br/>(`none`) | Subscribe to users entering or leaving the room<br/>Possible values: `all`, `in`, `out`, `none` |
| `subscribeToSelf` | <pre>bool</pre><br/>(`true`) | Subscribe to notifications fired by our own queries |
| `volatile` | <pre>const char\*</pre><br/>(`null`) | JSON string representing subscription information, used in [user join/leave notifications]({{site_base_path}}api/1/essentials/volatile-data/) |

## Return

The room ID.

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/cpp/1/error-handling).

## Usage

*Simple subscription to document notifications*

[snippet=document-notifications]

*Subscription to document notifications with scope option*

[snippet=document-notifications-leave-scope]

*Subscription to message notifications*

[snippet=message-notifications]

*Subscription to user notifications*

[snippet=user-notifications]

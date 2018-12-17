---
layout: sdk.html.hbs
title: unsubscribe
description: Removes a subscription
---

# unsubscribe

Removes a subscription.

## Signature

```csharp
public void unsubscribe(string roomId);
public void unsubscribe(string roomId, QueryOptions options);
```

### options

Additional query options

| Option     | Type<br/>(default)  | Description   |
| ---------- | ------- | --------------------------------- |
| `queuable` | <pre>bool</pre><br/>(`true`) | Make this request queuable or not |

## Exceptions

Throws a `Kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/csharp/1/essentials/error-handling).

## Usage

[snippet=unsubscribe]

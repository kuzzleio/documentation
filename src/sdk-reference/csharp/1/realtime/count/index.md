---
layout: sdk.html.hbs
title: count
description: Count subscribers for a subscription room
---

# count

Returns the number of other connections sharing the same subscription.

## Signature

```csharp
public int count(string roomId);
public int count(string roomId, QueryOptions options);
```

### options

Additional query options

| Option     | Type<br/>(default)  | Description   |
| ---------- | ------- | --------------------------------- |
| `queuable` | <pre>bool</pre><br/>(`true`) | Make this request queuable or not |

## Return

Returns the number of active connections using the same provided subscription room.

## Exceptions

Throws a `Kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/csharp/1/essentials/error-handling).

## Usage

[snippet=count]

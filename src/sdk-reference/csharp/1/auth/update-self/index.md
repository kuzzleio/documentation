---
layout: sdk.html.hbs
title: updateSelf
description: Updates the current user object in Kuzzle.
---

# updateSelf

Updates the current user object in Kuzzle.

## Signature

```csharp
public SWIGTYPE_p_User updateSelf(string content);
public SWIGTYPE_p_User updateSelf(string content, QueryOptions options);
```

## Arguments

| Arguments    | Type    | Description
|--------------|---------|-------------|
| `content` | <pre>string</pre> | JSON string representing the user content |
| `options`  | <pre>Kuzzleio::QueryOptions\*</pre>  | Query options |

### options

Additional query options:

| Option     | Type<br/>(default)   | Description  |
| ---------- | ------- | -------------- |
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Return

A [kuzzleio::User]({{ site_base_path }}sdk-reference/csharp/1/user/) object.

## Exceptions

Throws a `Kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/csharp/1/error-handling).

## Usage

[snippet=update-self]

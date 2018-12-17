---
layout: sdk.html.hbs
title: mGet
description: Get multiple documents from kuzzle
order: 200
---

# mGet

Gets multiple documents.

Throws a partial error (error code 206) if one or more document can not be retrieved.

## Signature

```csharp
public string mGet(string index, string collection, SWIGTYPE_p_std__vectorT_std__string_t ids);
public string mGet(string index, string collection, SWIGTYPE_p_std__vectorT_std__string_t ids, QueryOptions options);
```

### options

Additional query options

| Option | Type<br/>(default) | Description |
| ------ | -------------- | ----------- |
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again  |

## Return

Returns a JSON string containing the retrieved documents.

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/csharp/1/essentials/error-handling).

## Usage

[snippet=m-get]

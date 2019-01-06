---
layout: sdk.html.hbs
title: mDelete
description: Deletes multiple indexes
---

# mDelete

Deletes multiple indexes at once.

## Signature

```csharp
public SWIGTYPE_p_std__vectorT_std__string_t mDelete(SWIGTYPE_p_std__vectorT_std__string_t indexes);
public SWIGTYPE_p_std__vectorT_std__string_t mDelete(SWIGTYPE_p_std__vectorT_std__string_t indexes, QueryOptions options);
```

## Arguments

| Arguments | Type                       | Description  |
| --------- | -------------------------- | --------------|
| `indexes` | <pre>List<string></pre> | List of indexes |
| `options` | <pre>Kuzzleio::QueryOptions\*</pre>    | Query options |

### options

Additional query options

| Option     | Type<br/>(default)   | Description  |   
| ---------- | ------- | --------------------------------- | 
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Return

A vector of string containing the list of deleted indexes.

## Exceptions

Throws a `Kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/csharp/1/error-handling).

## Usage

[snippet=mDelete]

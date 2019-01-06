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
public string mGet(
    string index, 
    string collection, 
    SWIGTYPE_p_std__vectorT_std__string_t ids);

public string mGet(
    string index, 
    string collection, 
    SWIGTYPE_p_std__vectorT_std__string_t ids, 
    query_options options);

```

## Arguments

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>string</pre> | Index name |
| `collection` | <pre>string</pre> | Collection name |
| `ids` | <pre>std::vector&lt;stringgt;</pre> | Document IDs |
| `options` | <pre>Kuzzleio::QueryOptions\*</pre> | Query options |

### options

Additional query options

| Option | Type<br/>(default) | Description |
| ------ | -------------- | ----------- |
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again  |

## Return

A JSON string representing an array of objects containing the following properties:

| Property   | Type    | Description  |
|--------------|---------|-------------|
| `_id_` | <pre>string</pre> | Document ID |
| `_source_` | <pre>object</pre> | Document content |

## Exceptions

Throws a `Kuzzleio::KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/csharp/1/error-handling).

## Usage

[snippet=m-get]

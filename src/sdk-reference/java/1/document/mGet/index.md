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

```java
public String mGet(
    String index, 
    String collection, 
    StringVector ids);

public String mGet(
    String index, 
    String collection, 
    StringVector ids, 
    QueryOptions options);

```

## Arguments

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>String</pre> | Index name |
| `collection` | <pre>String</pre> | Collection name |
| `ids` | <pre>std::vector&lt;Stringgt;</pre> | Document IDs |
| `options` | <pre>io.kuzzle.sdk.QueryOptions</pre> | Query options |

### options

Additional query options

| Option | Type<br/>(default) | Description |
| ------ | -------------- | ----------- |
| `queuable` | <pre>boolean</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again  |

## Return

A JSON string representing an array of objects containing the following properties:

| Property   | Type    | Description  |
|--------------|---------|-------------|
| `_id_` | <pre>string</pre> | Document ID |
| `_source_` | <pre>object</pre> | Document content |

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/java/1/error-handling).

## Usage

[snippet=m-get]

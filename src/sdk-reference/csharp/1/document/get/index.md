---
layout: sdk.html.hbs
title: get
description: Get a document from kuzzle
order: 200
---

# get

Gets a document.

## Signature

```csharp
public string get(string index, string collection, string id);

public string get(
    string index, 
    string collection, 
    string id, 
    query_options options);

```

## Arguments

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>string</pre> | Index name |
| `collection` | <pre>string</pre> | Collection name |
| `id` | <pre>string</pre> | Document ID |
| `options` | <pre>Kuzzleio::QueryOptions\*</pre> | Query options |

### options

Additional query options

| Option   | Type<br/>(default) | Description                       |
| ---------- | ------- | --------------------------------- |
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again  |

## Return

A JSON string representing the document content.

| Property | Type | Description
| --- | --- | ---
| _source | <pre>object</pre> | Document content

## Exceptions

Throws a `Kuzzleio::KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/csharp/1/error-handling).

## Usage

[snippet=get]

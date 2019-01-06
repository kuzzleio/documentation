---
layout: sdk.html.hbs
title: mReplace
description: Replace documents
order: 200
---

# mReplace

Replaces multiple documents.

Throws a partial error (error code 206) if one or more documents can not be replaced.

## Signature

```csharp
public string mReplace(string index, string collection, string documents);

public string mReplace(
    string index, 
    string collection, 
    string documents, 
    query_options options);

```

## Arguments

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>string</pre> | Index name |
| `collection` | <pre>string</pre> | Collection name |
| `documents` | <pre>string</pre> | JSON string representing the documents to replace |
| `options` | <pre>Kuzzleio::QueryOptions</pre> | Query options |

### options

Additional query options

| Option | Type<br/>(default) | Description |
| ------ | -------------- | ----------- |
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again  |
| `refresh` | <pre>string</pre><br/>(`""`) | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |

## Return

A JSON string representing an object containing the following properties:

| Property   | Type    | Description  |
|--------------|---------|-------------|
| `hits` | <pre>object[]</pre> | Array of replaced documents |
| `total` | <pre>number</pre> | Total documents replaced |

## Exceptions

Throws a `Kuzzleio::KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/csharp/1/error-handling).

## Usage

[snippet=m-replace]

---
layout: sdk.html.hbs
title: mCreate
description: Creates multiple documents in kuzzle
order: 200
---

# mCreate

Creates multiple documents.

Throws a partial error (error code 206) if one or more documents creations fail.

## Signature

```csharp
public string mCreate(string index, string collection, string documents);

public string mCreate(
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
| `documents` | <pre>string</pre> | JSON string representing the documents to create |
| `options` | <pre>Kuzzleio::QueryOptions</pre> | Query options |

### options

Additional query options

| Option | Type<br/>(default) | Description |
| ------ | -------------- | ----------- |
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again  |
| `refresh` | <pre>string</pre><br/>(`""`) | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |

## Return

A JSON string containing an array representing the created documents.

## Exceptions

Throws a `Kuzzleio::KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/csharp/1/error-handling).

## Usage

[snippet=m-create]

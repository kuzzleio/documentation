---
layout: sdk.html.hbs
title: mUpdate
description: Update documents
order: 200
---

# mUpdate

Updates multiple documents.

Returns a partial error (error code 206) if one or more documents can not be updated.

Conflicts may occur if the same document gets updated multiple times within a short timespan in a database cluster.

You can set the `retryOnConflict` optional argument (with a retry count), to tell Kuzzle to retry the failing updates the specified amount of times before rejecting the request with an error.

## Signature

```csharp
public string mUpdate(string index, string collection, string documents);

public string mUpdate(
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
| `documents` | <pre>string</pre> | JSON string representing the documents to update |
| `options` | <pre>Kuzzleio::QueryOptions</pre> | Query options |

### options

Additional query options

| Option | Type<br/>(default) | Description |
| ------ | -------------- | ----------- |
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again  |
| `refresh` | <pre>string</pre><br/>(`""`) | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |
| `retryOnConflict` | <pre>int</pre><br/>(`0`) | The number of times the database layer should retry in case of version conflict |

## Return

A JSON string representing an object containing the following properties:

| Property   | Type    | Description  |
|--------------|---------|-------------|
| `hits` | <pre>object[]</pre> | Array of updated documents |
| `total` | <pre>number</pre> | Total documents updated |

## Exceptions

Throws a `Kuzzleio::KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/csharp/1/error-handling).

## Usage

[snippet=m-update]
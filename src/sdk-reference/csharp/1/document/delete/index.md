---
layout: sdk.html.hbs
title: delete
description: Deletes a document from kuzzle
order: 200
---

# delete

Deletes a document.

The optional parameter `refresh` can be used with the value `wait_for` in order to wait for the document to be indexed (and to be immediately available in search).

## Signature

```csharp
public string delete(string index, string collection, string id);

public string delete(
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
| `options` | <pre>Kuzzleio::QueryOptions</pre> | Query options |

### Options

Additional query options

| Option   | Type<br/>(default)   | Description                       |
| ---------- | ------- | --------------------------------- |
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again  |
| `refresh` | <pre>string</pre><br/>(`""`) | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |

## Return

The ID of the deleted document.

## Exceptions

Throws a `Kuzzleio::KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/csharp/1/error-handling).

## Usage

[snippet=delete]

---
layout: sdk.html.hbs
title: createOrReplace
description: Creates or replaces a document
---

# createOrReplace

Creates a new document in the persistent data storage, or replaces its content if it already exists.

The optional parameter `refresh` can be used with the value `wait_for` in order to wait for the document to be indexed (indexed documents are available for `search`).

## Signature

```csharp
public string createOrReplace(
    string index, 
    string collection, 
    string id, 
    string document);

public string createOrReplace(
    string index, 
    string collection, 
    string id, 
    string document, 
    query_options options);

```

## Arguments

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>string</pre> | Index name |
| `collection` | <pre>string</pre> | Collection name |
| `id` | <pre>string</pre> | Document ID |
| `document` | <pre>string</pre> | JSON string representing the body of the document |
| `options` | <pre>Kuzzleio::QueryOptions\*</pre> | Query options |

### options

Additional query options

| Option   | Type<br/>(default)    | Description                       |
| ---------- | ------- | --------------------------------- |
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again  |
| `refresh` | <pre>string</pre><br/>(`""`) | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |

## Return

A JSON string representing an object containing the document creation result.

| Property | Type | Description
| --- | --- | ---
| _id | <pre>string</pre> | ID of the newly created document
| _version | <pre>number</pre> | Version of the document in the persistent data storage
| _source | <pre>object</pre> | JSON string representing the created document
| result | <pre>string</pre> | Set to `created` in case of success

## Exceptions

Throws a `Kuzzleio::KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/csharp/1/error-handling).

## Usage

[snippet=create-or-replace]

---
layout: sdk.html.hbs
title: mDelete
description: Delete a document
order: 200
---

# mDelete

Deletes multiple documents.

Throws a partial error (error code 206) if one or more document deletions fail.

The optional parameter `refresh` can be used with the value `wait_for` in order to wait for the document indexation (indexed documents are available for `search`).

## Signature

```csharp
public SWIGTYPE_p_std__vectorT_std__string_t mDelete(
    string index, 
    string collection, 
    SWIGTYPE_p_std__vectorT_std__string_t ids);

public SWIGTYPE_p_std__vectorT_std__string_t mDelete(
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
| `ids` | <pre>std::vector&lt;stringgt;</pre> | IDs of the documents to delete |
| `options` | <pre>Kuzzleio::QueryOptions</pre> | Query options |

### options

Additional query options

| Option | Type<br/>(default) | Description |
| ------ | -------------- | ----------- |
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again  |
| `refresh` | <pre>string</pre><br/>(`""`) | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |

## Return

A vector containing the deleted documents IDs.

## Exceptions

Throws a `Kuzzleio::KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/csharp/1/error-handling).

## Usage

[snippet=m-delete]

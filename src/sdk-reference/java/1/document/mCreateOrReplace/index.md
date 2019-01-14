---
layout: sdk.html.hbs
title: mCreateOrReplace
description: Create or replace documents in kuzzle
order: 200
---

# mCreateOrReplace

Creates or replaces multiple documents.

Throws a partial error (error code 206) if one or more document creations/replacements fail.

## Signature

```java
public String mCreateOrReplace(
    String index, 
    String collection, 
    String documents);

public String mCreateOrReplace(
    String index, 
    String collection, 
    String documents, 
    QueryOptions options);

```

## Arguments

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>String</pre> | Index name |
| `collection` | <pre>String</pre> | Collection name |
| `body` | <pre>String</pre> | A JSON string containing the documents to create |
| `options` | <pre>io.kuzzle.sdk.QueryOptions</pre> | Query options |

### options

Additional query options

| Option | Type<br/>(default) | Description |
| ------ | -------------- | ----------- |
| `queuable` | <pre>boolean</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again  |
| `refresh` | <pre>String<br/>(`""`)</pre> | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |

## Return

Returns an JSON string containing the created documents.

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/java/1/error-handling).

## Usage

[snippet=m-create-or-replace]

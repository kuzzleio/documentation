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

```java
public String delete(
    String index, 
    String collection, 
    String id);

public String delete(
    String index, 
    String collection, 
    String id, 
    QueryOptions options);

public synchronized void delete();

```

## Arguments

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>String</pre> | Index name |
| `collection` | <pre>String</pre> | Collection name |
| `id` | <pre>String</pre> | Document ID |
| `options` | <pre>io.kuzzle.sdk.QueryOptions</pre> | Query options |

### Options

Additional query options

| Option   | Type<br/>(default)   | Description                       |
| ---------- | ------- | --------------------------------- |
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again  |
| `refresh` | <pre>String<br/>(`""`)</pre> | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |

## Return

The ID of the deleted document.

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/java/1/error-handling).

## Usage

[snippet=delete]

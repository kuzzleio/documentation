---
layout: sdk.html.hbs
title: get
description: Get a document from kuzzle
order: 200
---

# get

Gets a document.

## Signature

```java
public String get(
    String index, 
    String collection, 
    String id);

public String get(
    String index, 
    String collection, 
    String id, 
    QueryOptions options);

```

## Arguments

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>String</pre> | Index name |
| `collection` | <pre>String</pre> | Collection name |
| `id` | <pre>String</pre> | Document ID |
| `options` | <pre>io.kuzzle.sdk.QueryOptions</pre> | Query options |

### options

Additional query options

| Option   | Type<br/>(default) | Description                       |
| ---------- | ------- | --------------------------------- |
| `queuable` | <pre>boolean</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again  |

## Return

A JSON string representing the document content.

| Property | Type | Description
| --- | --- | ---
| _source | <pre>object</pre> | Document content

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/java/1/error-handling).

## Usage

[snippet=get]

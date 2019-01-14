---
layout: sdk.html.hbs
title: validate
description: Validate a document
order: 200
---

# validate

Validates data against existing validation rules.

Documents are always valid if no validation rules are defined on the provided index and collection.

This request does not store the document.


## Signature

```java
public boolean validate(
    String index, 
    String collection, 
    String document);

public boolean validate(
    String index, 
    String collection, 
    String document, 
    QueryOptions options);

```

## Arguments

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>String</pre> | Index name |
| `collection` | <pre>String</pre> | Collection name |
| `document` | <pre>String</pre> | JSON string representing the document |
| `options` | <pre>io.kuzzle.sdk.QueryOptions</pre> | Query options |

### options

Additional query options

| Option | Type<br/>(default) | Description |
| ------ | -------------- | ----------- |
| `queuable` | <pre>boolean</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again  |

## Return

A booleanean set to true if the document is valid and false otherwise.

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/java/1/error-handling).

## Usage

[snippet=validate]

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

```csharp
public bool validate(string index, string collection, string document);

public bool validate(
    string index, 
    string collection, 
    string document, 
    query_options options);

```

## Arguments

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>string</pre> | Index name |
| `collection` | <pre>string</pre> | Collection name |
| `document` | <pre>string</pre> | JSON string representing the document |
| `options` | <pre>Kuzzleio::QueryOptions</pre> | Query options |

### options

Additional query options

| Option | Type<br/>(default) | Description |
| ------ | -------------- | ----------- |
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again  |

## Return

A bool set to true if the document is valid and false otherwise.

## Exceptions

Throws a `Kuzzleio::KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/csharp/1/error-handling).

## Usage

[snippet=validate]

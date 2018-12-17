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
public bool validate(string index, string collection, string body);
public bool validate(string index, string collection, string body, QueryOptions options);
```

### options

Additional query options

| Option | Type<br/>(default) | Description |
| ------ | -------------- | ----------- |
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again  |

## Return

Returns a `bool` value set to true if the document is valid and false otherwise.

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/csharp/1/essentials/error-handling).

## Usage

[snippet=validate]

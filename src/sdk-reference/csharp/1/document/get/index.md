---
layout: sdk.html.hbs
title: get
description: Get a document from kuzzle
order: 200
---

# get

Gets a document.

## Signature

```csharp
public string get(string index, string collection, string id);
public string get(string index, string collection, string id, QueryOptions options);
```

### options

Additional query options

| Option   | Type<br/>(default) | Description                       |
| ---------- | ------- | --------------------------------- |
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again  |

## Return

Returns a JSON string containing the document.

| Name | Type | Description
| --- | --- | ---
| _source | <pre>string</pre> | A JSON string representing the retrieved document

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/csharp/1/essentials/error-handling).

## Usage

[snippet=get]

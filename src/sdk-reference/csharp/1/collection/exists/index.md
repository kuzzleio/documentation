---
layout: sdk.html.hbs
title: exists
description: Check if collection exists
---

# exists

Check if a collection exists in Kuzzle.

## Signature

```csharp
public bool exists(string index, string collection);

public bool exists(string index, string collection, query_options options);

```

## Arguments

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| `index` | <pre>string</pre> | Index name    | 
| `collection` | <pre>string</pre> | Collection name    |
| `options` | <pre>Kuzzleio::QueryOptions</pre> | Query options    | 

### options

Additional query options

| Property     | Type<br/>(default)    | Description        |
| ---------- | ------- | --------------------------------- | 
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Return

A bool indicating if the collection exists or not.

## Exceptions

Throws a `Kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/csharp/1/error-handling).

## Usage

[snippet=exists]

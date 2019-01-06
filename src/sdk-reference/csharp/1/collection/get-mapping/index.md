---
layout: sdk.html.hbs
title: getMapping
description: Return collection mapping
---

# getMapping

Returns the mapping for the given collection.

## Signature

```csharp
public string getMapping(string index, string collection);

public string getMapping(string index, string collection, query_options options);

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

A JSON string representing the collection data mapping.

## Usage

[snippet=get-mapping]

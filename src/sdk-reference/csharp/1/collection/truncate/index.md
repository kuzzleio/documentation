---
layout: sdk.html.hbs
title: truncate
description: Removes all documents from collection
---

# truncate

Removes all documents from a collection while keeping the associated mapping.  
It is faster than deleting all documents from a collection.

## Signature

```csharp
public void truncate(string index, string collection);

public void truncate(string index, string collection, QueryOptions options);

```

## Arguments

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| `index` | <pre>string</pre> | Index name    | 
| `collection` | <pre>string</pre> | Collection name    |
| `options` | <pre>Kuzzleio::QueryOptions\*</pre> | Query options    | 

### options

Additional query options

| Property     | Type<br/>(default)    | Description        |
| ---------- | ------- | --------------------------------- | 
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Usage

[snippet=truncate]

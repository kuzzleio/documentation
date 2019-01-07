---
layout: sdk.html.hbs
title: deleteSpecifications
description: Delete validation specifications for a collection
---

# deleteSpecifications

Deletes the validation specifications associated with the collection.  

## Signature

```csharp
public void deleteSpecifications(string index, string collection);

public void deleteSpecifications(
    string index, 
    string collection, 
    QueryOptions options);

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

[snippet=delete-specifications]

---
layout: sdk.html.hbs
title: getSpecifications
description: Returns the validation specifications
---

# getSpecifications

Returns the validation specifications associated to the collection.

## Signature

```csharp
public string getSpecifications(string index, string collection);
public string getSpecifications(string index, string collection, QueryOptions options);
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

## Return

A JSON string representing the validation specifications.

## Usage

[snippet=get-specifications]

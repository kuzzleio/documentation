---
layout: sdk.html.hbs
title: truncate
description: Remove all documents from collection
---

# truncate

Remove all documents from a collection while keeping the associated mapping.  
It is faster than deleting all documents from a collection.

## Signature

```cpp
void truncate(const std::string& index, const std::string& collection, kuzzleio::query_options *options=nullptr)
```

## Arguments

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| `index` | <pre>const std::string&</pre> | Index name    | 
| `collection` | <pre>const std::string&</pre> | Collection name    |
| `options` | <pre>kuzzleio::query_options*</pre> | Query options    | 

### options

Additional query options

| Property     | Type<br/>(default)    | Description        |
| ---------- | ------- | --------------------------------- | 
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Usage

[snippet=truncate]

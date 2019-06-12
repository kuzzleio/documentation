---
code: true
type: page
title: getMapping
description: Return collection mapping
---

# getMapping

Returns the mapping for the given collection.

## Signature

```cpp
std::string getMapping(const std::string& index, const std::string& collection);

std::string getMapping(
    const std::string& index,
    const std::string& collection,
    const kuzzleio::query_options& options);
```

## Arguments

| Arguments    | Type                                 | Description     |
| ------------ | ------------------------------------ | --------------- |
| `index`      | <pre>const std::string&</pre>        | Index name      |
| `collection` | <pre>const std::string&</pre>        | Collection name |
| `options`    | <pre>kuzzleio::query_options\*</pre> | Query options   |

### options

Additional query options

| Property   | Type<br/>(default)           | Description                                                                  |
| ---------- | ---------------------------- | ---------------------------------------------------------------------------- |
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Return

A JSON string representing the collection data mapping.

## Usage

<<< ./snippets/get-mapping.cpp

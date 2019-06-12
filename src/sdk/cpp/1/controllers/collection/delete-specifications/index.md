---
code: true
type: page
title: deleteSpecifications
description: Delete validation specifications for a collection
---

# deleteSpecifications

Delete the validation specifications associated with the collection.

## Signature

```cpp
void deleteSpecifications(
    const std::string& index,
    const std::string& collection);

void deleteSpecifications(
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

## Usage

<<< ./snippets/delete-specifications.cpp

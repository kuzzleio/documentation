---
code: true
type: page
title: mGet
description: Get multiple documents from kuzzle
---

# mGet

Gets multiple documents.

Throws a partial error (error code 206) if one or more document can not be retrieved.

## Signature

```cpp
std::string mGet(
    const std::string& index,
    const std::string& collection,
    const std::vector<std::string>& ids);

std::string mGet(
    const std::string& index,
    const std::string& collection,
    const std::vector<std::string>& ids,
    const kuzzleio::query_options& options);
```

## Arguments

| Argument     | Type                                      | Description     |
| ------------ | ----------------------------------------- | --------------- |
| `index`      | <pre>const std::string&</pre>             | Index name      |
| `collection` | <pre>const std::string&</pre>             | Collection name |
| `ids`        | <pre>std::vector&lt;std::string&gt;</pre> | Document IDs    |
| `options`    | <pre>kuzzleio::query_options\*</pre>      | Query options   |

### options

Additional query options

| Option     | Type<br/>(default)           | Description                                                                  |
| ---------- | ---------------------------- | ---------------------------------------------------------------------------- |
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Return

A JSON string representing an array of objects containing the following properties:

| Property   | Type              | Description      |
| ---------- | ----------------- | ---------------- |
| `_id_`     | <pre>string</pre> | Document ID      |
| `_source_` | <pre>object</pre> | Document content |

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle errors](/sdk/cpp/1/essentials/error-handling).

## Usage

<<< ./snippets/m-get.cpp

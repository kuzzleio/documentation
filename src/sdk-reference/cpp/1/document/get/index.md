---
layout: sdk.html.hbs
algolia: true
title: get
description:
order: 200
---

# get

Gets a document.

## Arguments

```cpp
std::string get(
    const std::string& index, 
    const std::string& collection, 
    const std::string& id, 
    kuzzleio::query_options *options=nullptr
)
```

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>std::string</pre> | Index name |
| `collection` | <pre>std::string</pre> | Collection name |
| `id` | <pre>std::string</pre> | The document id |
| `options` | <pre>query_options</pre> | A pointer to a `query_options` containing query options |

### options

Additional query options

| Option   | Type (default) | Description                       |
| ---------- | ------- | --------------------------------- |
| `queuable` | <pre>boolean</pre> (`true`) | If true, queues the request during downtime, until connected to Kuzzle again  |

## Return

Returns a JSON string containing the document.

| Name | Type | Description
| --- | --- | ---
| _source | <pre>object</pre> | The retrieved document

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/cpp/1/essentials/error-handling).

## Usage

[snippet=get]

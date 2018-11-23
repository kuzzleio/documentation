---
layout: sdk.html.hbs
algolia: true
title: get
description: Get a document from kuzzle
order: 200
algolia: true
---

# get

Gets a document.

## Arguments

```cpp
std::string get(
    const std::string& index,
    const std::string& collection,
    const std::string& id,
    kuzzleio::query_options *options=nullptr)
```

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>const std::string&</pre> | Index name |
| `collection` | <pre>const std::string&</pre> | Collection name |
| `id` | <pre>const std::string&</pre> | The document id |
| `options` | <pre>kuzzleio::query_options*</pre> | A pointer to a `kuzzleio::query_options` containing query options |

### options

Additional query options

| Option   | Type<br/>(default) | Description                       |
| ---------- | ------- | --------------------------------- |
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again  |

## Return

Returns a JSON string containing the document.

| Name | Type | Description
| --- | --- | ---
| _source | <pre>std::string</pre> | A JSON string representing the retrieved document

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/cpp/1/essentials/error-handling).

## Usage

[snippet=get]

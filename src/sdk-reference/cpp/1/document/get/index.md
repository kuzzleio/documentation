---
layout: sdk.html.hbs
title: get
description: Get a document from kuzzle
order: 200
---

# get

Gets a document.

## Signature

```cpp
std::string get(
    const std::string& index,
    const std::string& collection,
    const std::string& id,
    kuzzleio::query_options *options=nullptr)
```

## Arguments

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>const std::string&</pre> | Index name |
| `collection` | <pre>const std::string&</pre> | Collection name |
| `id` | <pre>const std::string&</pre> | Document ID |
| `options` | <pre>kuzzleio::query_options\*</pre> | Query options |

### options

Additional query options

| Option   | Type<br/>(default) | Description                       |
| ---------- | ------- | --------------------------------- |
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again  |

## Return

A JSON string representing the document content.

| Property | Type | Description
| --- | --- | ---
| _source | <pre>object</pre> | Document content

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/cpp/1/error-handling).

## Usage

[snippet=get]

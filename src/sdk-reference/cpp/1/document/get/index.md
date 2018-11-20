---
layout: sdk.html.hbs
algolia: true
title: get
description: Get a document from kuzzle
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
    kuzzleio::query_options *options=nullptr)
```

| Argument | Type | Description |
| | _source | <pre>std::string</pre> | A JSON string representing the retrieved document

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/cpp/1/essentials/error-handling).

## Usage

[snippet=get]

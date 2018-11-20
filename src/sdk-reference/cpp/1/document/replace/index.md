---
layout: sdk.html.hbs
algolia: true
title: replace
description: Replace a document
order: 200
---


# replace

Replaces the content of an existing document.

## Arguments

```cpp
std::string replace(
    const std::string& index,
    const std::string& collection,
    const std::string& id,
    const std::string& body,
    kuzzleio::query_options *options=nullptr)
```

| Argument | Type | Description |
| | _id | <pre>std::string</pre> | The id of the newly created document
| _version | <pre>int</pre> | The version of the document in the persistent data storage
| result | <pre>std::string</pre> | set to `updated` in case of success

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/cpp/1/essentials/error-handling).

## Usage

[snippet=replace]

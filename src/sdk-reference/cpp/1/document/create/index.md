---
layout: sdk.html.hbs
algolia: true
title: create
description: Create a new document
---


# create

Creates a new document in the persistent data storage.

Throws an error if the document already exists.

The optional parameter `refresh` can be used with the value `wait_for` in order to wait for the document to be indexed (indexed documents are available for `search`).

## Arguments

```cpp
std::string create(
    const std::string& index,
    const std::string& collection,
    const std::string& id,
    const std::string& body,
    kuzzleio::query_options *options=nullptr);
```

| Argument | Type | Description |
| | _id | <pre>const std::string&</pre> | ID of the newly created document
| _version | <pre>int</pre> | Version of the document in the persistent data storage
| _source | <pre>const std::string&</pre> | A JSON string containing the created document
| result | <pre>const std::string&</pre> | Set to `created` in case of success

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/cpp/1/essentials/error-handling).

## Usage

[snippet=create]

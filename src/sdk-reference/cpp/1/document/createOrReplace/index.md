---
layout: sdk.html.hbs
algolia: true
title: createOrReplace
description: Creates or replaces a document
---


# createOrReplace

Creates a new document in the persistent data storage, or replaces its content if it already exists.

The optional parameter `refresh` can be used with the value `wait_for` in order to wait for the document to be indexed (indexed documents are available for `search`).

## Arguments

```cpp
std::string Document::createOrReplace(
    const std::string& index,
    const std::string& collection,
    const std::string& id,
    const std::string& body,
    <pre>kuzzleio::query_options*</pre> *options)
```
<br/>

| Argument | Type | Description |
| | _id | <pre>const std::string&</pre> | The id of the newly created document
| _version | <pre>int</pre> | The version of the document in the persistent data storage
| _source | <pre>const std::string&</pre> | The created document
| result | <pre>const std::string&</pre> | set to `created` in case of success and `updated` if the document already exists

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/cpp/1/essentials/error-handling).

## Usage

[snippet=create-or-replace]

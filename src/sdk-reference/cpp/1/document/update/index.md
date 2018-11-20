---
layout: sdk.html.hbs
algolia: true
title: update
description: Update a document
order: 200
---


# update

Updates a document content.

Conflicts may occur if the same document gets updated multiple times within a short timespan, in a database cluster.
You can set the `retryOnConflict` optional argument (with a retry count), to tell Kuzzle to retry the failing updates the specified amount of times before rejecting the request with an error.

## Arguments

```cpp
std::string update(
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

[snippet=update]

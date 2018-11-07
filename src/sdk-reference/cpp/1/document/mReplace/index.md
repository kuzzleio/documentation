---
layout: sdk.html.hbs
algolia: true
title: mReplace
description:
order: 200
---

# mReplace

Replaces multiple documents.

Throws a partial error (error code 206) if one or more documents can not be replaced.

## Arguments

```cpp
std::string mReplace(
    const std::string& index, 
    const std::string& collection, 
    const std::string& documents, 
    kuzzleio::query_options *options=nullptr
)
```

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>std::string</pre> | Index name |
| `collection` | <pre>std::string</pre> | Collection name |
| `documents` | <pre>std::string</pre> | A JSON string containing the documents to update |
| `options` | <pre>query_options</pre> | A pointer to a `query_options` containing query options |

### options

Additional query options

| Option | Type (default) | Description |
| ------ | -------------- | ----------- |
| `queuable` | <pre>boolean</pre> (`true`) | If true, queues the request during downtime, until connected to Kuzzle again  |
| `refresh` | <pre>std::string</pre> | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |

## Return

Returns a JSON string containing the updated documents.

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/cpp/1/essentials/error-handling).

## Usage

[snippet=m-replace]

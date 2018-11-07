---
layout: sdk.html.hbs
algolia: true
title: delete
description:
order: 200
---

# delete_

Deletes a document.

The optional parameter `refresh` can be used with the value `wait_for` in order to wait for the document to be indexed (and to no longer be available in search).

## Arguments

```cpp
std::string delete_(
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

### Options

Additional query options

| Option   | Type (default)   | Description                       |
| ---------- | ------- | --------------------------------- |
| `queuable` | <pre>boolean</pre> (`true`) | If true, queues the request during downtime, until connected to Kuzzle again  |
| `refresh` | <pre>std::string</pre> | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |

## Return

Returns the id of the deleted document.

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/cpp/1/essentials/error-handling).

## Usage

[snippet=delete]

---
layout: sdk.html.hbs
algolia: true
title: mDelete
description:
order: 200
---

# mDelete

Deletes documents in the persistent data storage.

Returns a [partial error]({{ site_base_path }}api-documentation/errors/#partialerror) (with status 206) if one or more document can not be deleted.

The optional parameter `refresh` can be used
with the value `wait_for` in order to wait for the document indexation (indexed documents are available for `search`).

## Arguments

```cpp
std::vector<std::string> mDelete(
    const std::string& index, 
    const std::string& collection, 
    const std::vector<std::string>& ids, 
    kuzzleio::query_options *options=nullptr
)
```

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>std::string</pre> | Index name |
| `collection` | <pre>std::string</pre> | Collection name |
| `ids` | <pre>std::vector&lt;std::string&gt;</pre> | The ids of the documents to delete |
| `options` | <pre>query_options</pre> | A pointer to a `query_options` containing query options |

### options

Additional query options

| Option | Type (default) | Description |
| ------ | -------------- | ----------- |
| `queuable` | <pre>boolean</pre> (`true`) | Make this request queuable or not |
| `refresh` | <pre>std::string</pre> | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |

## Return

Returns a `std::vector< std::string>` containing ids of the deleted documents.

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/cpp/1/essentials/error-handling).

## Usage

[snippet=m-delete]

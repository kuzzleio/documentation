---
layout: sdk.html
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

## Signature

```cpp
std::vector<std::string> mDelete(const std::string& index, const std::string& collection, const std::vector<std::string>& ids, query_options *options=nullptr);
```

## Arguments

| Arguments | Type | Description |
| --- | --- | --- |
| `index` | std::string | Index name |
| `collection` | std::string | Collection name |
| `ids` | std::vector<std::string> | The ids of the documents to delete |
| `options` | query_options | A pointer to a `query_options` containing query options |

### Options

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |
| `refresh` | std::string | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) | `` |

## Return

Returns a `std::vector< std::string>` containing ids of the deleted documents.

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/essentials/error-handling).

## Usage

[snippet=m-delete]

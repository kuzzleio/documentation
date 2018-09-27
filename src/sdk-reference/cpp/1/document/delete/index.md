---
layout: sdk.html
algolia: true
title: delete
description:
order: 200
---

# delete_

Given a `documentId`, deletes the corresponding document from Kuzzle.

Only documents in the persistent data storage layer can be deleted.

The optional parameter refresh can be used with the value wait_for in order to wait for the document to be deleted (and to no longer be available in search).

## Signature

```cpp
std::string delete_(const std::string& index, const std::string& collection, const std::string& id, query_options *options=nullptr);
```

## Arguments

| Arguments | Type | Description | Required |
| --- | --- | --- | --- |
| `index` | std::string | Index name | yes |
| `collection` | std::string | Collection name | yes |
| `id` | std::string | The document id | yes |
| `options` | query_options | A pointer to a `query_options` containing query options | no |

### Options

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |
| `refresh` | std::string | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |

## Return

Returns the id of the deleted document.

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/essentials/error-handling).

## Usage

[snippet=delete]

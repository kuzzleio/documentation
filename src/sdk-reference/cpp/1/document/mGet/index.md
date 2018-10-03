---
layout: sdk.html
algolia: true
title: mGet
description:
order: 200
---

# mGet

Given `document ids`, retrieves the corresponding documents from the database.

Only documents in the persistent data storage layer can be retrieved.

Returns a [partial error]({{ site_base_path }}api-documentation/errors/#partialerror) (with status 206) if one or more document can not be retrieved.

## Signature

```cpp
std::string mGet(const std::string& index, const std::string& collection, const std::vector<std::string>& ids, query_options *options=nullptr)
```

## Arguments

| Arguments | Type | Description | Required |
| --- | --- | --- | --- |
| `index` | std::string | Index name | yes |
| `collection` | std::string | Collection name | yes |
| `ids` | std::vector<std::string> | The document ids | yes |
| `options` | query_options | A pointer to a `query_options` containing query options | no |

### Options

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |
| `includeTrash` | boolean | If set to `true`, includes the documents from the trash | `false`  |

## Return

Returns a JSON string containing the retreived documents.

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/essentials/error-handling).

## Usage

[snippet=m-get]

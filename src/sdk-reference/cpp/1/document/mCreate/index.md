---
layout: sdk.html.hbs
algolia: true
title: mCreate
description:
order: 200
---

# mCreate

Creates new documents in the persistent data storage.

Returns a partial error (with status 206) if one or more documents fail to create.


## Signature

```cpp
std::string mCreate(const std::string& index, const std::string& collection, const std::string& body, query_options *options=nullptr)
```

## Arguments

| Arguments | Type | Description |
| --- | --- | --- |
| `index` | std::string | Index name |
| `collection` | std::string | Collection name |
| `body` | std::string | A JSON string containing the documents to create |
| `options` | query_options | A pointer to a `query_options` containing query options |

### Options

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |
| `refresh` | std::string | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) | |

## Return

Returns an JSON string containing the created documents.

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/essentials/error-handling).

## Usage

[snippet=m-create]

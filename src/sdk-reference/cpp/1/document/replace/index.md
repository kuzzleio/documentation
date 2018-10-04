---
layout: sdk.html.hbs
algolia: true
title: replace
description:
order: 200
---

# replace

Replaces an existing document in the persistent data storage.
Only documents in the persistent data storage layer can be replaced.

## Signature

```cpp
std::string replace(const std::string& index, const std::string& collection, const std::string& id, const std::string& body, query_options *options=nullptr)
```

## Arguments

| Arguments | Type | Description |
| --- | --- | --- |
| `index` | std::string | Index name |
| `collection` | std::string | Collection name |
| `id` | std::string | The document id |
| `body` | std::string | A JSON string containing the body of the document |
| `options` | query_options | A pointer to a `query_options` containing query options |

### Options

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |
| `refresh` | std::string | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) | `` |

## Return

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/essentials/error-handling).

## Usage

[snippet=replace]

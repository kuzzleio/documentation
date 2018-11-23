---
layout: sdk.html.hbs
algolia: true
title: mReplace
description: Replace documents
order: 200
algolia: true
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
    kuzzleio::query_options *options=nullptr)
```

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>const std::string&</pre> | Index name |
| `collection` | <pre>const std::string&</pre> | Collection name |
| `documents` | <pre>const std::string&</pre> | A JSON string containing the documents to update |
| `options` | <pre>kuzzleio::query_options*</pre> | A pointer to a `kuzzleio::query_options` containing query options |

### options

Additional query options

| Option | Type<br/>(default) | Description |
| ------ | -------------- | ----------- |
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again  |
| `refresh` | <pre>const std::string&<br/>(`""`)</pre> | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |

## Return

Returns a JSON string containing the updated documents.

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/cpp/1/essentials/error-handling).

## Usage

[snippet=m-replace]

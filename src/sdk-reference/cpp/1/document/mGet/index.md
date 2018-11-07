---
layout: sdk.html.hbs
algolia: true
title: mGet
description:
order: 200
---

# mGet

Gets multiple documents.

Throws a partial error (error code 206) if one or more document can not be retrieved.


## Arguments

```cpp
std::string mGet(
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
| `ids` | <pre>std::vector<std::string></pre> | The document ids |
| `options` | <pre>query_options</pre> | A pointer to a `query_options` containing query options |

### options

Additional query options

| Option | Type (default) | Description |
| ------ | -------------- | ----------- |
| `queuable` | <pre>boolean</pre> (`true`) | If true, queues the request during downtime, until connected to Kuzzle again  |

## Return

Returns a JSON string containing the retreived documents.

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/cpp/1/essentials/error-handling).

## Usage

[snippet=m-get]

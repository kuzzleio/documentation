---
layout: sdk.html.hbs
algolia: true
title: validate
description:
order: 200
---

# validate

Validates data against existing validation rules. 

Documents are always valid if no validation rules are defined on the provided index and collection.

This request does **not** store or publish the document.


## Arguments

```cpp
bool validate(
    const std::string& index, 
    const std::string& collection, 
    const std::string& body, 
    kuzzleio::query_options *options=nullptr
)
```

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>std::string</pre> | Index name |
| `collection` | <pre>std::string</pre> | Collection name |
| `body` | <pre>std::string</pre> | A JSON string containing the body of the document |
| `options` | <pre>query_options</pre> | A pointer to a `query_options` containing query options |

### options

Additional query options

| Option | Type (default) | Description |
| ------ | -------------- | ----------- |
| `queuable` | <pre>boolean</pre> (`true`) | If true, queues the request during downtime, until connected to Kuzzle again  |

## Return

Returns a <pre>boolean</pre> value set to true if the document is valid and false otherwise.

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/cpp/1/essentials/error-handling).

## Usage

[snippet=validate]

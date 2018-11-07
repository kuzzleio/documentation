---
layout: sdk.html.hbs
algolia: true
title: create
description:
order: 200
---

# create

Create a new document in Kuzzle

Throws if a document with the same given id already exists in Kuzzle.

The optional parameter `refresh` can be used with the value `wait_for` in order to wait for the document to be indexed (indexed documents are available for `search`).

## Arguments

```cpp
std::string create(
    const std::string& index, 
    const std::string& collection, 
    const std::string& id, 
    const std::string& body, 
    kuzzleio::query_options *options=nullptr
);
```

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>std::string</pre> | Index name |
| `collection` | <pre>std::string</pre> | Collection name |
| `id` | <pre>std::string</pre> | Optional document id. If set to a blank string, will use a auto-generated id |
| `body` | <pre>std::string</pre> | A JSON string containing the body of the document |
| `options` | <pre>query_options</pre> | A pointer to a `query_options` containing query options |

### options

Additional query options

| Option   | Type (default)    | Description                       |
| ---------- | ------- | --------------------------------- |
| `queuable` | <pre>boolean</pre> (`true`) | Make this request queuable or not |
| `refresh` | <pre>std::string</pre> | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |

## Return

Returns a JSON string containing the document creation result.

| Name | Type | Description
| --- | --- | ---
| _id | <pre>string</pre> | The id of the newly created document
| _version | <pre>int</pre> | The version of the document in the persistent data storage
| _source | <pre>object</pre> | The created document
| result | <pre>string</pre> | set to `created` in case of success

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/cpp/1/essentials/error-handling).

## Usage

[snippet=create]

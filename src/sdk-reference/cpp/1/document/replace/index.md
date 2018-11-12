---
layout: sdk.html.hbs
algolia: true
title: replace
description: Replace a document
order: 200
---

# replace

Replaces the content of an existing document.

## Arguments

```cpp
std::string replace(
    const std::string& index,
    const std::string& collection,
    const std::string& id,
    const std::string& body,
    kuzzleio::query_options *options=nullptr)
```

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>const std::string&</pre> | Index name |
| `collection` | <pre>const std::string&</pre> | Collection name |
| `id` | <pre>const std::string&</pre> | The document id |
| `body` | <pre>const std::string&</pre> | A JSON string containing the body of the document |
| `options` | <pre>kuzzleio::query_options*</pre> | A pointer to a `kuzzleio::query_options` containing query options |

### options

Additional query options

| Option | Type<br/>(default) | Description |
| ------ | -------------- | ----------- |
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again  |
| `refresh` | <pre>const std::string&<br/>(`""`)</pre> | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |

## Return

Returns a JSON string containing the document update result.

| Name | Type | Description
| --- | --- | ---
| _id | <pre>std::string</pre> | The id of the newly created document
| _version | <pre>int</pre> | The version of the document in the persistent data storage
| result | <pre>std::string</pre> | set to `updated` in case of success

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/cpp/1/essentials/error-handling).

## Usage

[snippet=replace]

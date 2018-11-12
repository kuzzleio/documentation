---
layout: sdk.html.hbs
algolia: true
title: createOrReplace
description: Creates or replaces a document
---

# createOrReplace

Creates a new document in the persistent data storage, or replaces its content if it already exists.

The optional parameter `refresh` can be used with the value `wait_for` in order to wait for the document to be indexed (indexed documents are available for `search`).

## Arguments

```cpp
std::string Document::createOrReplace(
    const std::string& index,
    const std::string& collection,
    const std::string& id,
    const std::string& body,
    <pre>kuzzleio::query_options*</pre> *options)
```
<br/>

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>const std::string&</pre> | Index name |
| `collection` | <pre>const std::string&</pre> | Collection name |
| `id` | <pre>const std::string& | The document id |
| `body` | <pre>const std::string&</pre> | A JSON string containing the body of the document |
| `options` | <pre>kuzzleio::query_options*</pre> | A pointer to a `kuzzleio::query_options` containing query options |

### options

Additional query options

| Option   | Type<br/>(default)    | Description                       |
| ---------- | ------- | --------------------------------- |
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again  |
| `refresh` | <pre>const std::string&<br/>(`""`)</pre><br/>(`""`) | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |

## Return

Returns a JSON string containing the document creation result.

| Name | Type | Description
| --- | --- | ---
| _id | <pre>const std::string&</pre> | The id of the newly created document
| _version | <pre>int</pre> | The version of the document in the persistent data storage
| _source | <pre>const std::string&</pre> | The created document
| result | <pre>const std::string&</pre> | set to `created` in case of success and `updated` if the document already exists

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/essentials/error-handling).

## Usage

[snippet=create-or-replace]

---
layout: sdk.html.hbs
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
    const std::string& document,
    kuzzleio::query_options* options)
```
<br/>

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>const std::string&</pre> | Index name |
| `collection` | <pre>const std::string&</pre> | Collection name |
| `id` | <pre>const std::string&</pre> | Document ID |
| `document` | <pre>const std::string&</pre> | JSON string representing the body of the document |
| `options` | <pre>kuzzleio::query_options*</pre> | Query options |

### options

Additional query options

| Option   | Type<br/>(default)    | Description                       |
| ---------- | ------- | --------------------------------- |
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again  |
| `refresh` | <pre>const std::string&</pre><br/>(`""`) | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |

## Return

A JSON string representing an object containing the document creation result.

| Property | Type | Description
| --- | --- | ---
| _id | <pre>string</pre> | ID of the newly created document
| _version | <pre>number</pre> | Version of the document in the persistent data storage
| _source | <pre>object</pre> | JSON string representing the created document
| result | <pre>string</pre> | Set to `created` in case of success

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/cpp/1/essentials/error-handling).

## Usage

[snippet=create-or-replace]

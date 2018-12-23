---
layout: sdk.html.hbs
title: create
description: Create a new document
---

# create

Creates a new document in the persistent data storage.

Throws an error if the document already exists.

The optional parameter `refresh` can be used with the value `wait_for` in order to wait for the document to be indexed (indexed documents are available for `search`).

## Arguments

```cpp
std::string create(
    const std::string& index,
    const std::string& collection,
    const std::string& id,
    const std::string& document,
    kuzzleio::query_options *options=nullptr);
```

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>const std::string&</pre> | Index name |
| `collection` | <pre>const std::string&</pre> | Collection name |
| `id` | <pre>const std::string&</pre> | Document ID. Will use an auto-generated id if not specified |
| `document` | <pre>const std::string&</pre> | JSON string representing the body of the document |
| `options` | <pre>kuzzleio::query_options*</pre> | Query options |

### options

Additional query options

| Option   | Type<br/>(default)    | Description                       |
| ---------- | ------- | --------------------------------- |
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again  |
| `refresh` | <pre>const std::string&</pre><br/>(`""`)| If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |

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

[snippet=create]

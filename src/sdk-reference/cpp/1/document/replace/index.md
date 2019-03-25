---
layout: sdk.html.hbs
title: replace
description: Replace a document
---

# replace

Replaces the content of an existing document.

## Signature

```cpp
std::string replace(
    const std::string& index, 
    const std::string& collection, 
    const std::string& id, 
    const std::string& document);

std::string replace(
    const std::string& index, 
    const std::string& collection, 
    const std::string& id, 
    const std::string& document, 
    const kuzzleio::query_options& options);
```

## Arguments

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>const std::string&</pre> | Index name |
| `collection` | <pre>const std::string&</pre> | Collection name |
| `id` | <pre>const std::string&</pre> | Document ID |
| `document` | <pre>const std::string&</pre> | JSON string representing the document |
| `options` | <pre>kuzzleio::query_options\*</pre> | Query options |

### options

Additional query options

| Option | Type<br/>(default) | Description |
| ------ | -------------- | ----------- |
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again  |
| `refresh` | <pre>const std::string&<br/>(`""`)</pre> | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |

## Return

A JSON string representing an object containing the document creation result.

| Property | Type | Description
| --- | --- | ---
| _id | <pre>string</pre> | ID of the newly created document
| _version | <pre>number</pre> | Version of the document in the persistent data storage
| _source | <pre>object</pre> | JSON string representing the replaced document
| result | <pre>string</pre> | Set to `replaced` in case of success

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/cpp/1/error-handling).

## Usage

[snippet=replace]

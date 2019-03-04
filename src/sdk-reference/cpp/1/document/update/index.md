---
layout: sdk.html.hbs
title: update
description: Update a document
---

# update

Updates a document content.

Conflicts may occur if the same document gets updated multiple times within a short timespan, in a database cluster.
You can set the `retryOnConflict` optional argument (with a retry count), to tell Kuzzle to retry the failing updates the specified amount of times before rejecting the request with an error.

## Signature

```cpp
std::string update(
    const std::string& index, 
    const std::string& collection, 
    const std::string& id, 
    const std::string& document);

std::string update(
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
| `refresh` | <pre>const std::string&<br/></pre>(`""`) | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |
| `retryOnConflict` | <pre>int</pre><br/>(`0`) | The number of times the database layer should retry in case of version conflict |

## Return

A JSON string representing an object containing the document creation result.

| Property | Type | Description
| --- | --- | ---
| _id | <pre>string</pre> | ID of the newly created document
| _version | <pre>number</pre> | Version of the document in the persistent data storage
| result | <pre>string</pre> | Set to `updated` in case of success

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/cpp/1/error-handling).

## Usage

[snippet=update]

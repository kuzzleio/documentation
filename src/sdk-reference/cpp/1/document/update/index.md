---
layout: sdk.html.hbs
algolia: true
title: update
description:
order: 200
---

# update

Update a document in Kuzzle.

Only documents in the persistent data storage layer can be updated.

The optional parameter `refresh` can be used
with the value `wait_for` in order to wait for the document indexation (indexed documents are available for `search`).

Conflicts may occur if the same document gets updated multiple times within a short time on a database cluster. When this happens, Kuzzle answers with an error that clients have to handle.  
You may set the `retryOnConflict` optional argument with a positive integer, asking Kuzzle to retry updating the document that number of times before rejecting the request with an error.

## Signature

```cpp
std::string update(const std::string& index, const std::string& collection, const std::string& id, const std::string& body, query_options *options=nullptr)
```

## Arguments

| Arguments | Type | Description |
| --- | --- | --- |
| `index` | std::string | Index name |
| `collection` | std::string | Collection name |
| `id` | std::string | The document id |
| `body` | std::string | A JSON string containing the body of the document |
| `options` | query_options | A pointer to a `query_options` containing query options |

### Options

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |
| `refresh` | std::string | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) | `` |
| `retryOnConflict` | int | The number of times the database layer should retry in case of version conflict | 0 |

## Return

Returns a JSON string containing the document creation result.

| Name | Type | Description
| --- | --- | ---
| _id | string | The id of the newly created document
| _version | int | The version of the document in the persistent data storage
| _source | object | The created document
| result | string | set to `created` in case of success

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/essentials/error-handling).

## Usage

[snippet=update]

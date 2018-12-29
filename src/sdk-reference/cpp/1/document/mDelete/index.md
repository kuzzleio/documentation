---
layout: sdk.html.hbs
title: mDelete
description: Delete a document
order: 200
---

# mDelete

Deletes multiple documents.

Throws a partial error (error code 206) if one or more document deletions fail.

The optional parameter `refresh` can be used with the value `wait_for` in order to wait for the document indexation (indexed documents are available for `search`).

## Signature

```cpp
std::vector<std::string> mDelete(
    const std::string& index, 
    const std::string& collection, 
    const std::vector<std::string>& ids)

std::vector<std::string> mDelete(
    const std::string& index, 
    const std::string& collection, 
    const std::vector<std::string>& ids, 
    const kuzzleio::query_options& options)
```

## Arguments

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>const std::string&</pre> | Index name |
| `collection` | <pre>const std::string&</pre> | Collection name |
| `ids` | <pre>std::vector&lt;std::string&gt;</pre> | IDs of the documents to delete |
| `options` | <pre>kuzzleio::query_options\*</pre> | Query options |

### options

Additional query options

| Option | Type<br/>(default) | Description |
| ------ | -------------- | ----------- |
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again  |
| `refresh` | <pre>const std::string&<br/>(`""`)</pre> | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |

## Return

A vector containing the deleted documents IDs.

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/cpp/1/essentials/error-handling).

## Usage

[snippet=m-delete]

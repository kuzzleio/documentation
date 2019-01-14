---
layout: sdk.html.hbs
title: delete
description: Deletes a document from kuzzle
---

# delete_

Deletes a document.

The optional parameter `refresh` can be used with the value `wait_for` in order to wait for the document to be indexed (and to be immediately available in search).

## Signature

```cpp
std::string delete_(
    const std::string& index, 
    const std::string& collection, 
    const std::string& id);

std::string delete_(
    const std::string& index, 
    const std::string& collection, 
    const std::string& id, 
    const kuzzleio::query_options& options);
```

## Arguments

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>const std::string&</pre> | Index name |
| `collection` | <pre>const std::string&</pre> | Collection name |
| `id` | <pre>const std::string&</pre> | Document ID |
| `options` | <pre>kuzzleio::query_options\*</pre> | Query options |

### Options

Additional query options

| Option   | Type<br/>(default)   | Description                       |
| ---------- | ------- | --------------------------------- |
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again  |
| `refresh` | <pre>const std::string&<br/>(`""`)</pre> | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |

## Return

The ID of the deleted document.

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/cpp/1/error-handling).

## Usage

[snippet=delete]

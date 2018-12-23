---
layout: sdk.html.hbs
title: mCreate
description: Creates multiple documents in kuzzle
order: 200
---

# mCreate

Creates multiple documents.

Throws a partial error (error code 206) if one or more documents creations fail.

## Signature

```cpp
std::string mCreate(
    const std::string& index,
    const std::string& collection,
    const std::string& documents,
    kuzzleio::query_options *options=nullptr)
```

## Arguments

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>const std::string&</pre> | Index name |
| `collection` | <pre>const std::string&</pre> | Collection name |
| `documents` | <pre>const std::string&</pre> | JSON string representing the documents to create |
| `options` | <pre>kuzzleio::query_options\*</pre> | Query options |

### options

Additional query options

| Option | Type<br/>(default) | Description |
| ------ | -------------- | ----------- |
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again  |
| `refresh` | <pre>const std::string&<br/>(`""`)</pre> | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |

## Return

A JSON string containing an array representing the created documents.

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/cpp/1/essentials/error-handling).

## Usage

[snippet=m-create]

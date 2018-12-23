---
layout: sdk.html.hbs
title: mReplace
description: Replace documents
order: 200
---

# mReplace

Replaces multiple documents.

Throws a partial error (error code 206) if one or more documents can not be replaced.

## Arguments

```cpp
std::string mReplace(
    const std::string& index,
    const std::string& collection,
    const std::string& documents,
    kuzzleio::query_options *options=nullptr)
```

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>const std::string&</pre> | Index name |
| `collection` | <pre>const std::string&</pre> | Collection name |
| `documents` | <pre>const std::string&</pre> | JSON string representing the documents to replace |
| `options` | <pre>kuzzleio::query_options\*</pre> | Query options |

### options

Additional query options

| Option | Type<br/>(default) | Description |
| ------ | -------------- | ----------- |
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again  |
| `refresh` | <pre>const std::string&<br/>(`""`)</pre> | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |

## Return

A JSON string representing an object containing the following properties:

| Property   | Type    | Description  |
|--------------|---------|-------------|
| `hits` | <pre>object[]</pre> | Array of replaced documents |
| `total` | <pre>number</pre> | Total documents replaced |

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/cpp/1/essentials/error-handling).

## Usage

[snippet=m-replace]

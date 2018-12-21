---
layout: sdk.html.hbs
title: mGet
description: Get multiple documents from kuzzle
order: 200
---

# mGet

Gets multiple documents.

Throws a partial error (error code 206) if one or more document can not be retrieved.

## Arguments

```cpp
std::string mGet(
    const std::string& index,
    const std::string& collection,
    const std::vector<std::string>& ids,
    kuzzleio::query_options *options=nullptr)
```

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>const std::string&</pre> | Index name |
| `collection` | <pre>const std::string&</pre> | Collection name |
| `ids` | <pre>std::vector&lt;std::string&gt;</pre> | Document IDs |
| `options` | <pre>kuzzleio::query_options*</pre> | Query options |

### options

Additional query options

| Option | Type<br/>(default) | Description |
| ------ | -------------- | ----------- |
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again  |

## Return

A JSON string representing an object containing the following properties:

| Property   | Type    | Description  |
|--------------|---------|-------------|
| `hits` | <pre>object[]</pre> | Array of document |
| `total` | <pre>number</pre> | Total documents count |


## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/cpp/1/essentials/error-handling).

## Usage

[snippet=m-get]

---
layout: sdk.html.hbs
title: list
description: Returns the collection list of an index
---

# list

Returns the complete list of realtime and stored data collections in requested index sorted by name in alphanumerical order.  
The `from` and `size` arguments allow pagination. They are returned in the response if provided.


## Signature

```cpp
std::string list(const std::string& index)

std::string list(const std::string& index, const kuzzleio::query_options& options)
```

## Arguments

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| `index` | <pre>const std::string&</pre> | Index name    | 
| `options` | <pre>kuzzleio::query_options\*</pre> | Query options    | 

### options

Additional query options

| Property     | Type<br/>(default)    | Description        |
| ---------- | ------- | --------------------------------- | 
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |
| `from` | <pre>long</pre><br/>(`0`) | Offset of the first result |
| `size` | <pre>long</pre><br/>(`10`) | Maximum number of returned results |

## Return

A JSON string representing the following object:

| Property   | Type    | Description  |
|--------------|---------|-------------|
| `type` | <pre>string</pre> | Types of returned collections <br/>(`all`, `realtime` or `stored`)   |
| ``collections`` | <pre>object[]</pre> | List of collections  |
| `from` | <pre>number</pre> | Offset of the first result |
| `size` | <pre>number</pre> | Maximum number of returned results |

Each object in the `collections` array contains the following properties:

| Property   | Type    | Description  |
|--------------|---------|-------------|
| `name` | <pre>string</pre> | Collection name |
| `type` | <pre>string</pre> | Collection type (`realtime` or `stored`) |


## Usage

[snippet=list]

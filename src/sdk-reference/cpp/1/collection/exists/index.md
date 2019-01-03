---
layout: sdk.html.hbs
title: exists
description: Check if collection exists
---

# exists

Check if a collection exists in Kuzzle.

## Signature

```cpp
bool exists(const std::string& index, const std::string& collection);

bool exists(
    const std::string& index, 
    const std::string& collection, 
    const kuzzleio::query_options& options);
```

## Arguments

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| `index` | <pre>const std::string&</pre> | Index name    | 
| `collection` | <pre>const std::string&</pre> | Collection name    |
| `options` | <pre>kuzzleio::query_options\*</pre> | Query options    | 

### options

Additional query options

| Property     | Type<br/>(default)    | Description        |
| ---------- | ------- | --------------------------------- | 
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Return

A boolean indicating if the collection exists or not.

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/cpp/1/error-handling).

## Usage

[snippet=exists]

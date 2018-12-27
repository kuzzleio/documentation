---
layout: sdk.html.hbs
title: getStrategies
description: Get all authentication strategies registered in Kuzzle.
---

# getStrategies

Get all authentication strategies registered in Kuzzle.

## Signature

```cpp
std::vector<std::string> getStrategies(query_options *options=nullptr);
```

## Arguments

| Arguments    | Type    | Description
|--------------|---------|-------------
| `options`  | <pre>kuzzleio::query_options\*</pre>    | Query options

### options

Additional query options

| Property     | Type<br/>(default)    | Description        | 
| ---------- | ------- | --------------------------------- | 
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Return

A vector of string representing the available authentication strategies.

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/cpp/1/essentials/error-handling).

## Usage

[snippet=get-strategies]

---
layout: sdk.html.hbs
title: getStrategies
description: Get all authentication strategies registered in Kuzzle.
---

# getStrategies

Get all authentication strategies registered in Kuzzle.

## Signature

```csharp
public SWIGTYPE_p_std__vectorT_std__string_t getStrategies();

public SWIGTYPE_p_std__vectorT_std__string_t getStrategies(
    query_options options);

```

## Arguments

| Arguments    | Type    | Description
|--------------|---------|-------------
| `options`  | <pre>Kuzzleio::QueryOptions\*</pre>    | Query options

### options

Additional query options

| Property     | Type<br/>(default)    | Description        | 
| ---------- | ------- | --------------------------------- | 
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Return

A vector of string representing the available authentication strategies.

## Exceptions

Throws a `Kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/csharp/1/error-handling).

## Usage

[snippet=get-strategies]

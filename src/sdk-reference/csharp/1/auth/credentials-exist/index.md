---
layout: sdk.html.hbs
title: credentialsExist
description: Check that the current user has credentials for the specified strategy
---

# credentialsExist

Check that the current user has credentials for the specified strategy.

## Signature

```csharp
public bool credentialsExist(string strategy);
public bool credentialsExist(string strategy, QueryOptions options);
```

## Arguments

| Arguments  | Type             | Description                                             |
| ---------- | ---------------- | ------------------------------------------------------- |
| `strategy` | <pre>string</pre>      | Strategy to use                                         |
| `options`  | <pre>Kuzzleio::QueryOptions\*</pre>    | Query options

### options

Additional query options

| Property     | Type<br/>(default)    | Description        | 
| ---------- | ------- | --------------------------------- | 
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |


## Return

A bool indicating if credentials exists for the strategy.

## Exceptions

Throws a `Kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/csharp/1/error-handling).

## Usage

[snippet=credentials-exist]

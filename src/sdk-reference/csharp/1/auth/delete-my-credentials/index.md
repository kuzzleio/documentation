---
layout: sdk.html.hbs
title: deleteMyCredentials
description: Delete the current user's credentials for the specified strategy
---

# deleteMyCredentials

Delete the current user's credentials for the specified strategy. If the credentials that generated the current JWT are removed, the user will remain logged in until he logs out or his session expires, after that they will no longer be able to log in with the deleted credentials.

## Signature

```csharp
public void deleteMyCredentials(string strategy);

public void deleteMyCredentials(string strategy, query_options options);

```

## Arguments

| Arguments    | Type    | Description | 
|--------------|---------|-------------|
| `strategy` | <pre>string</pre> | Strategy to use
| `options`  | <pre>Kuzzleio::QueryOptions\* </pre>   | Query options


### options

Additional query options

| Property     | Type<br/>(default)    | Description        | 
| ---------- | ------- | --------------------------------- | 
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Exceptions

Throws a `Kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/csharp/1/error-handling).

## Usage

[snippet=delete-my-credentials]

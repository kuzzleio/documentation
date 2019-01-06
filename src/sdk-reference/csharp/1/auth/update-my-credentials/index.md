---
layout: sdk.html.hbs
title: updateMyCredentials
description: Update the current user's credentials for the specified strategy.
---

# updateMyCredentials

Update the current user's credentials for the specified strategy. The credentials to send will depend on the authentication plugin and the authentication strategy.

## Signature

```csharp
public string updateMyCredentials(string strategy, string credentials);

public string updateMyCredentials(
    string strategy, 
    string credentials, 
    query_options options);

```

## Arguments

| Arguments    | Type    | Description
|--------------|---------|-------------
| `strategy` | <pre>string</pre> | Strategy to use
| `credentials` | <pre>string</pre> | JSON string representing the credentials
| `options`  | <pre>Kuzzleio::QueryOptions\*</pre>    | Query options


### options

Additional query options

| Property     | Type<br/>(default)    | Description        | 
| ---------- | ------- | --------------------------------- | 
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |


## Exceptions

Throws a `Kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/csharp/1/error-handling).

## Return

A JSON string representing the updated credentials.

## Usage

[snippet=update-my-credentials]

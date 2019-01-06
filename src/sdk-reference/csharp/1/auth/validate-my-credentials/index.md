---
layout: sdk.html.hbs
title: validateMyCredentials
description: Validate the current user's credentials for the specified strategy.
---

# validateMyCredentials

Validate the current user's credentials for the specified strategy. The `result` field is `true` if the provided credentials are valid; otherwise an error is triggered. This route does not actually create or modify the user credentials. The credentials to send will depend on the authentication plugin and authentication strategy.

## Signature

```csharp
public bool validateMyCredentials(string strategy, string credentials);

public bool validateMyCredentials(
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

## Return

A bool indicating if the credentials are valid.

## Usage

[snippet=validate-my-credentials]

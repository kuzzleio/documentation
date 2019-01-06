---
layout: sdk.html.hbs
title: createMyCredentials
description: Create the current user's credentials for the specified strategy.
---

# createMyCredentials

Create the current user's credentials for the specified strategy.

## Signature

```csharp
public string createMyCredentials(string strategy, string credentials);
public string createMyCredentials(string strategy, string credentials, QueryOptions options);
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

A JSON string representing the new credentials.

## Usage

[snippet=create-my-credentials]

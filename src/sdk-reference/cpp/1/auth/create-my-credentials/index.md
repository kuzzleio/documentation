---
layout: sdk.html.hbs
title: createMyCredentials
description: Create the current user's credentials for the specified strategy.
---

# createMyCredentials

Create the current user's credentials for the specified strategy.

## Signature

```cpp
std::string createMyCredentials(const std::string& strategy, const std::string& credentials, kuzzleio::query_options* options=nullptr);
```

## Arguments

| Arguments    | Type    | Description
|--------------|---------|-------------
| `strategy` | <pre>const std::string&</pre> | Strategy to use
| `credentials` | <pre>const std::string&</pre> | JSON string representing the credentials
| `options`  | <pre>kuzzleio::query_options*</pre>    | Query options


### options

Additional query options

| Property     | Type<br/>(default)    | Description        | 
| ---------- | ------- | --------------------------------- | 
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |


## Return

A JSON string representing the new credentials.

## Usage

[snippet=create-my-credentials]

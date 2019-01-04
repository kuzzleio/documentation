---
layout: sdk.html.hbs
title: deleteMyCredentials
description: Delete the current user's credentials for the specified strategy
---

# deleteMyCredentials

Delete the current user's credentials for the specified strategy. If the credentials that generated the current JWT are removed, the user will remain logged in until he logs out or his session expires, after that they will no longer be able to log in with the deleted credentials.

## Signature

```cpp
void deleteMyCredentials(const std::string& strategy);

void deleteMyCredentials(
    const std::string& strategy, 
    const kuzzleio::query_options& options);
```

## Arguments

| Arguments    | Type    | Description | 
|--------------|---------|-------------|
| `strategy` | <pre>const std::string&</pre> | Strategy to use
| `options`  | <pre>kuzzleio::query_options\* </pre>   | Query options


### options

Additional query options

| Property     | Type<br/>(default)    | Description        | 
| ---------- | ------- | --------------------------------- | 
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/cpp/1/error-handling).

## Usage

[snippet=delete-my-credentials]

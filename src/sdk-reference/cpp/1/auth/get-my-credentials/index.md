---
layout: sdk.html.hbs
title: getMyCredentials
description: Returns the current user's credential information for the specified strategy.
---

# getMyCredentials

Returns the current user's credential information for the specified strategy. The data returned will depend on the specified strategy. The result can be an empty string.

## Signature

```cpp
std::string getMyCredentials(const std::string& strategy)

std::string getMyCredentials(
    const std::string& strategy, 
    const kuzzleio::query_options& options);
```

## Arguments

| Arguments    | Type    | Description | 
|--------------|---------|-------------|
| `strategy` | <pre>const std::string&</pre> | Strategy to use    |
| `options`  | <pre>kuzzleio::query_options\*</pre>    | Query options

### options

Additional query options

| Property     | Type<br/>(default)    | Description        | 
| ---------- | ------- | --------------------------------- | 
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Return

Returns a JSON string representing the credentials for the provided authentication strategy.

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/cpp/1/essentials/error-handling).

## Usage

[snippet=get-my-credentials]

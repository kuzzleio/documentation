---
layout: sdk.html.hbs
title: credentialsExist
description: Check that the current user has credentials for the specified strategy
---

# credentialsExist

Check that the current user has credentials for the specified strategy.

## Signature

```cpp
 bool credentialsExist(const std::string& strategy, kuzzleio::query_options *options=nullptr);
```

## Arguments

| Arguments  | Type             | Description                                             |
| ---------- | ---------------- | ------------------------------------------------------- |
| `strategy` | <pre>const std::string&</pre>      | Strategy to use                                         |
| `options`  | <pre>kuzzleio::query_options\*</pre>    | Query options

### **Options**

Additional query options

| Property     | Type<br/>(default)    | Description        | 
| ---------- | ------- | --------------------------------- | 
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |


## Return

A boolean indicating if credentials exists for the strategy.

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/cpp/1/essentials/error-handling).

## Usage

[snippet=credentials-exist]

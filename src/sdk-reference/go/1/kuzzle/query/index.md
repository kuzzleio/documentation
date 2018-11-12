---
layout: sdk.html.hbs
algolia: true
title: query
description: Base method to send API query to Kuzzle
---

# query

Base method used to send queries to Kuzzle, following the [API Documentation]({{ site_base_path }}api/1).

<div class="alert alert-warning">
This is a low-level method, exposed to allow advanced SDK users to bypass high-level methods.
</div>

## Signature

```go
Query(request *types.KuzzleRequest, options types.QueryOptions, responseChannel chan<- *types.KuzzleResponse)
```

## Arguments

| Argument          | Type                          | Description                           | Required |
| ----------------- | ----------------------------- | ------------------------------------- | -------- |
| `request`         | \*types.KuzzleRequest         | API request options                   | yes      |
| `options`         | types.QueryOptions            | Additional query options              | yes      |
| `responseChannel` | chan<- \*types.KuzzleResponse | A channel to receive the API response | yes      |

### **request**

Properties required for the Kuzzle API can be set in the [KuzzleRequest](https://github.com/kuzzleio/sdk-go/blob/master/types/kuzzle_request.go).  
The following properties are the most common.

| Property     | Type         | Description                               | Required |
| ------------ | ------------ | ----------------------------------------- | -------- |
| `Controller` | string       | Controller name                           | yes      |
| `Action`     | string       | Action name                               | yes      |
| `Body`       | interface{}  | Query body for this action                | no       |
| `Index`      | string       | Index name for this action                | no       |
| `Collection` | string       | Collection name for this action           | no       |
| `Id`         | string       | id for this action                        | no       |
| `Volatile`   | VolatileData | Additional informations to send to Kuzzle | no       |

### **options**

A [QueryOptions](https://github.com/kuzzleio/sdk-go/blob/master/types/query_options.go) containing additional Query options  
Theses properties can bet Get/Set.  
The following properties are the most common.

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `Queuable` | bool | Make this request queuable or not | true    |

### **responseChannel**

A channel to receive the API response.  
This channel will receive a [KuzzleResponse](https://github.com/kuzzleio/sdk-go/blob/master/types/kuzzle_response.go)

## Usage

[snippet=query]

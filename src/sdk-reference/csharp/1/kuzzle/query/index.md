---
layout: sdk.html.hbs
title: query
description: Base method to send API query to Kuzzle
---

# query

Base method used to send queries to Kuzzle, following the [API Documentation]({{ site_base_path }}api/1).

<div class="alert alert-warning">
This is a low-level method, exposed to allow advanced SDK users to bypass high-level methods.
</div>

## Signature

```csharp
public KuzzleResponse query(KuzzleRequest query);
public KuzzleResponse query(KuzzleRequest query, QueryOptions options);
```

## Arguments

| Argument  | Type             | Description              | Required |
| --------- | ---------------- | ------------------------ | -------- |
| `request` | kuzzle_request\* | API request options      | yes      |
| `options` | Kuzzleio::QueryOptions\*  | Additional query options | no       |

### **request**

Properties required for the Kuzzle API can be set in the [kuzzle_request](https://github.com/kuzzleio/sdk-go/blob/1.x/internal/wrappers/headers/kuzzlesdk.h#L51) class.
The following properties are the most common.

| Property     | Type         | Description                                                        | Required |
| ------------ | ------------ | ------------------------------------------------------------------ | -------- |
| `controller` |  string | Controller name                                                    | yes      |
| `action`     |  string | Action name                                                        | yes      |
| `body`       |  string | JSON query body string for this action                             | no       |
| `index`      |  string | Index name for this action                                         | no       |
| `collection` |  string | Collection name for this action                                    | no       |
| `id`         |  string | id for this action                                                 | no       |
| `volatiles`  |  string | JSON string representing additional information to send to Kuzzle | no       |

### **options**

A [query_option](https://github.com/kuzzleio/sdk-go/blob/1.x/internal/wrappers/headers/kuzzlesdk.h#L169) containing additional query options
The following properties are the most common.

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | bool | Make this request queuable or not | true    |

## Exceptions

Throws a `Kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/csharp/1/essentials/error-handling).

## Exceptions

Throws a `Kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/csharp/1/essentials/error-handling).

## Exceptions

Throws a `Kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/csharp/1/essentials/error-handling).


---
layout: sdk.html.hbs
title: refresh
description: Force Elasticsearch search index update
---

# Refresh

When writing or deleting documents in Kuzzle, the update needs to be indexed before being available in search results.

<div class="alert alert-info">
  A refresh operation comes with some performance costs.

From [Elasticsearch documentation](https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-refresh.html):
"While a refresh is much lighter than a commit, it still has a performance cost. A manual refresh can be useful when writing tests, but don’t do a manual refresh every time you index a document in production; it will hurt your performance. Instead, your application needs to be aware of the near real-time nature of Elasticsearch and make allowances for it."

</div>

## Signature

```csharp
public void refresh(string index);
public void refresh(string index, QueryOptions options);
```

## Arguments

| Arguments | Type          | Description                                             | Required |
| --------- | ------------- | ------------------------------------------------------- | -------- |
| `index`   | string   | Index name                                              | yes      |
| `options` | Kuzzleio::QueryOptions | A `Kuzzleio::QueryOptions` containing query options | no       |

### **Options**

Additional query options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | bool | Make this request queuable or not | `true`  |

## Exceptions

Throws a `Kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/csharp/1/essentials/error-handling).

## Usage

[snippet=refresh]

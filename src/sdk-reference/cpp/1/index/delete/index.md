---
layout: sdk.html.hbs
title: delete
description: Deletes an index
---

# Delete

Deletes an entire data index from Kuzzle.

## Signature

```cpp
void delete_(const std::string& index, kuzzleio::query_options *options = null)
```

## Arguments

| Arguments | Type          | Description                                             | Required |
| --------- | ------------- | ------------------------------------------------------- | -------- |
| `index`   | const std::string&   | Index name                                              | yes      |
| `options` | kuzzleio::query_options* | A pointer to a `kuzzleio::query_options` containing query options | no       |

### options

Additional query options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | 
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/cpp/1/essentials/error-handling).

## Usage

[snippet=delete]

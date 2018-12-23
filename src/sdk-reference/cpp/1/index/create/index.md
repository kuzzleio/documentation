---
layout: sdk.html.hbs
title: create
description: Create an index
---

# create

Create a new index in Kuzzle.

## Signature

```cpp
void create(const std::string& index, kuzzleio::query_options *options = null)
```

## Arguments

| Arguments | Type          | Description       |
| --------- | ------------- | ------------------|
| `index`   | <pre>const std::string&</pre>   | Index name     |
| `options` | <pre>kuzzleio::query_options*</pre> | Query options |

### options

Additional query options

| Option     | Type    | Description                       | 
| ---------- | ------- | --------------------------------- | 
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/cpp/1/essentials/error-handling).

## Usage

[snippet=create]

---
code: true
type: page
title: create
description: Create an index
---

# create

Create a new index in Kuzzle.

## Signature

```cpp
void create(const std::string& index);

void create(const std::string& index, const kuzzleio::query_options& options);
```

## Arguments

| Arguments | Type                                 | Description   |
| --------- | ------------------------------------ | ------------- |
| `index`   | <pre>const std::string&</pre>        | Index name    |
| `options` | <pre>kuzzleio::query_options\*</pre> | Query options |

### options

Additional query options

| Option     | Type<br/>(default)           | Description                                                                  |
| ---------- | ---------------------------- | ---------------------------------------------------------------------------- |
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle error](/sdk/cpp/1/essentials/error-handling).

## Usage

<<< ./snippets/create.cpp

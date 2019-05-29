---
code: true
type: page
title: delete_
description: Deletes an index
---

# delete\_

Deletes an entire index from Kuzzle.

## Signature

```cpp
void delete_(const std::string& index);

void delete_(const std::string& index, const kuzzleio::query_options& options);
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

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle error](/sdk/cpp/1/error-handling).

## Usage

<<< ./snippets/delete.cpp

---
code: true
type: page
title: mDelete
description: Deletes multiple indexes
---

# mDelete

Deletes multiple indexes at once.

## Signature

```cpp
std::vector<std::string> mDelete(const std::vector<std::string>& indexes);

std::vector<std::string> mDelete(
    const std::vector<std::string>& indexes,
    const kuzzleio::query_options& options);
```

## Arguments

| Arguments | Type                                       | Description     |
| --------- | ------------------------------------------ | --------------- |
| `indexes` | <pre>std::vector &lt;std::string&gt;</pre> | List of indexes |
| `options` | <pre>kuzzleio::query_options\*</pre>       | Query options   |

### options

Additional query options

| Option     | Type<br/>(default)           | Description                                                                  |
| ---------- | ---------------------------- | ---------------------------------------------------------------------------- |
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Return

A vector of string containing the list of deleted indexes.

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle error](/sdk/cpp/1/error-handling).

## Usage

<<< ./snippets/mDelete.cpp

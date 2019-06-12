---
code: true
type: page
title: updateSelf
description: Updates the current user object in Kuzzle.
---

# updateSelf

Updates the current user object in Kuzzle.

## Signature

```cpp
kuzzleio::User updateSelf(const std::string& content);

kuzzleio::User updateSelf(const std::string& content, const kuzzleio::query_options& options);
```

## Arguments

| Arguments | Type                                 | Description                               |
| --------- | ------------------------------------ | ----------------------------------------- |
| `content` | <pre>const std::string&</pre>        | JSON string representing the user content |
| `options` | <pre>kuzzleio::query_options\*</pre> | Query options                             |

### options

Additional query options:

| Option     | Type<br/>(default)           | Description                                                                  |
| ---------- | ---------------------------- | ---------------------------------------------------------------------------- |
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Return

A [kuzzleio::User](/sdk/cpp/1/core-classes/user/) object.

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle error](/sdk/cpp/1/essentials/error-handling).

## Usage

<<< ./snippets/update-self.cpp

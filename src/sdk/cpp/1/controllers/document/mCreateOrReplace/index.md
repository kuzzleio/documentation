---
code: true
type: page
title: mCreateOrReplace
description: Create or replace documents in kuzzle
---

# mCreateOrReplace

Creates or replaces multiple documents.

Throws a partial error (error code 206) if one or more document creations/replacements fail.

## Signature

```cpp
std::string mCreateOrReplace(
    const std::string& index,
    const std::string& collection,
    const std::string& documents);

std::string mCreateOrReplace(
    const std::string& index,
    const std::string& collection,
    const std::string& documents,
    const kuzzleio::query_options& options);
```

## Arguments

| Argument     | Type                                 | Description                                      |
| ------------ | ------------------------------------ | ------------------------------------------------ |
| `index`      | <pre>const std::string&</pre>        | Index name                                       |
| `collection` | <pre>const std::string&</pre>        | Collection name                                  |
| `body`       | <pre>const std::string&</pre>        | A JSON string containing the documents to create |
| `options`    | <pre>kuzzleio::query_options\*</pre> | Query options                                    |

### options

Additional query options

| Option     | Type<br/>(default)                       | Description                                                                        |
| ---------- | ---------------------------------------- | ---------------------------------------------------------------------------------- |
| `queuable` | <pre>bool</pre><br/>(`true`)             | If true, queues the request during downtime, until connected to Kuzzle again       |
| `refresh`  | <pre>const std::string&<br/>(`""`)</pre> | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |

## Return

Returns an JSON string containing the created documents.

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle errors](/sdk/cpp/1/essentials/error-handling).

## Usage

<<< ./snippets/m-create-or-replace.cpp

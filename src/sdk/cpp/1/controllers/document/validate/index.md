---
code: true
type: page
title: validate
description: Validate a document
---

# validate

Validates data against existing validation rules.

Documents are always valid if no validation rules are defined on the provided index and collection.

This request does not store the document.

## Signature

```cpp
bool validate(
    const std::string& index,
    const std::string& collection,
    const std::string& document);

bool validate(
    const std::string& index,
    const std::string& collection,
    const std::string& document,
    const kuzzleio::query_options& options);
```

## Arguments

| Argument     | Type                                 | Description                           |
| ------------ | ------------------------------------ | ------------------------------------- |
| `index`      | <pre>const std::string&</pre>        | Index name                            |
| `collection` | <pre>const std::string&</pre>        | Collection name                       |
| `document`   | <pre>const std::string&</pre>        | JSON string representing the document |
| `options`    | <pre>kuzzleio::query_options\*</pre> | Query options                         |

### options

Additional query options

| Option     | Type<br/>(default)           | Description                                                                  |
| ---------- | ---------------------------- | ---------------------------------------------------------------------------- |
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Return

A boolean set to true if the document is valid and false otherwise.

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle errors](/sdk/cpp/1/error-handling).

## Usage

<<< ./snippets/validate.cpp

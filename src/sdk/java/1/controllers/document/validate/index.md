---
code: true
type: page
title: validate
description: Validate a document
---

# validate

Validates data against existing validation rules.

Note that if no validation specifications are set for the `<index>`/`<collection>`, the document will always be valid.

This request does **not** store or publish the document.

## Arguments

```java
boolean validate(String index, String collection, String document)

boolean validate(
  String index,
  String collection,
  String document,
  io.kuzzle.sdk.QueryOptions options
)
```

<br/>

| Argument     | Type                                  | Description                                       |
| ------------ | ------------------------------------- | ------------------------------------------------- |
| `index`      | <pre>String</pre>                     | Index name                                        |
| `collection` | <pre>String</pre>                     | Collection name                                   |
| `body`       | <pre>String</pre>                     | A JSON string containing the body of the document |
| `options`    | <pre>io.kuzzle.sdk.QueryOptions</pre> | Query options                                     |

### options

Additional query options

| Option     | Type<br/>(default)              | Description                                                                  |
| ---------- | ------------------------------- | ---------------------------------------------------------------------------- |
| `queuable` | <pre>boolean</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Return

Returns a boolean value set to true if the document is valid and false otherwise.

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle error](/sdk/java/1/essentials/error-handling/).

## Usage

<<< ./snippets/validate.java

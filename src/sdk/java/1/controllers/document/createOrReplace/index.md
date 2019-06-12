---
code: true
type: page
title: createOrReplace
description: Creates or replaces a document
---

# createOrReplace

Creates a new document in the persistent data storage, or replaces its content if it already exists.

The optional parameter `refresh` can be used with the value `wait_for` in order to wait for the document to be indexed (indexed documents are available for `search`).

## Arguments

```java
String createOrReplace(
  String index,
  String collection,
  String id,
  String documents,
  io.kuzzle.sdk.QueryOptions options
)
String createOrReplace(
  String index,
  String collection,
  String id,
  String documents
)
```

<br/>

| Argument     | Type                                  | Description                                       |
| ------------ | ------------------------------------- | ------------------------------------------------- |
| `index`      | <pre>String</pre>                     | Index name                                        |
| `collection` | <pre>String</pre>                     | Collection name                                   |
| `id`         | <pre>String</pre>                     | Document ID                                       |
| `documents`  | <pre>String</pre>                     | A JSON String containing the body of the document |
| `options`    | <pre>io.kuzzle.sdk.QueryOptions</pre> | Query options                                     |

###### **Options**

Additional query options

| Option     | Type<br/>(default)              | Description                                                                        |
| ---------- | ------------------------------- | ---------------------------------------------------------------------------------- |
| `queuable` | <pre>boolean</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again       |
| `refresh`  | <pre>String</pre><br/>(`""`)    | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |

## Return

Returns a JSON string containing the updated/created document.

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle errors](/sdk/java/1/essentials/error-handling/).

## Usage

<<< ./snippets/create-or-replace.java

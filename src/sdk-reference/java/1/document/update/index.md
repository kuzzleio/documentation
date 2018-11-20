---
layout: sdk.html.hbs
algolia: true
title: update
description: Update a document
---


# update

Updates a document content.

Conflicts may occur if the same document gets updated multiple times within a short timespan, in a database cluster.
You can set the `retryOnConflict` optional argument (with a retry count), to tell Kuzzle to retry the failing updates the specified amount of times before rejecting the request with an error.

## Arguments

```java
String update(String index, String collection, String id, String document)

String update(
  String index,
  String collection,
  String id,
  String document,
  io.kuzzle.sdk.QueryOptions options
)
```

<br/>

| Argument | Type | Description |
| | _id | <pre>string</pre> | The id of the newly created document
| _version | <pre>int</pre> | The version of the document in the persistent data storage
| result | <pre>string</pre> | set to `updated` in case of success

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/java/1/essentials/error-handling).

## Usage

[snippet=update]

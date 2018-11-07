---
layout: sdk.html.hbs
algolia: true
title: update
description:
order: 200
---

# update

Update a document in Kuzzle.

Only documents in the persistent data storage layer can be updated.

Conflicts may occur if the same document gets updated multiple times within a short time on a database cluster. When this happens, Kuzzle answers with an error that clients have to handle.  
You may set the `retryOnConflict` optional argument with a positive integer, asking Kuzzle to retry updating the document that number of times before rejecting the request with an error.

## Arguments

```java
String update(String, String, String, String, io.kuzzle.sdk.QueryOptions)
String update(String, String, String, String)
```

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>String</pre> | Index name |
| `collection` | <pre>String</pre> | Collection name |
| `id` | <pre>String</pre> | The document id |
| `body` | <pre>String</pre> | A JSON string containing the body of the document |
| `options` | <pre>io.kuzzle.sdk.QueryOptions</pre> | The query options |

###### options

| Option | Type (default) | Description | 
| --- | --- | --- | 
| `queuable` | <pre>boolean</pre> (`true`)| Make this request queuable or not |
| `refresh` | <pre>String</pre> | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |
| `retryOnConflict` | <pre>int</pre> (`0`) | The number of times the database layer should retry in case of version conflict |

## Return

Returns a JSON string containing the document update result.

| Name | Type | Description
| --- | --- | ---
| _id | string | The id of the newly created document
| _version | int | The version of the document in the persistent data storage
| result | string | set to `updated` in case of success

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/java/1/essentials/error-handling).

## Usage

[snippet=update]

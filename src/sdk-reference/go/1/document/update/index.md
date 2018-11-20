---
layout: sdk.html.hbs
algolia: true
title: update
description: Update a document
---


# Update

Updates a document content.

Conflicts may occur if the same document gets updated multiple times within a short timespan, in a database cluster.
You can set the `retryOnConflict` optional argument (with a retry count), to tell Kuzzle to retry the failing updates the specified amount of times before rejecting the request with an error.

## Arguments

```go
Update(
  index string,
  collection string,
  id string,
  document json.RawMessage,
  options types.QueryOptions) (json.RawMessage, error)
```

<br/>

| Argument | Type | Description |
| | _id | <pre>string</pre> | Newly created document ID
| _version | int | Version of the document in the persistent data storage
| result | <pre>string</pre> | Set to `updated` in case of success

## Usage

[snippet=update]

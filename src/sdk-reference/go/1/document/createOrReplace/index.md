---
layout: sdk.html.hbs
algolia: true
title: createOrReplace
description:
order: 200
---

# createOrReplace

Creates a new document in the persistent data storage, or replace it if it already exists.

The optional parameter `refresh` can be used with the value `wait_for` in order to wait for the document to be indexed (indexed documents are available for `search`).

## Signature

```go
CreateOrReplace(index string, collection string, _id string, body json.RawMessage, options types.QueryOptions) (json.RawMessage, error)
```

## Arguments

| Arguments | Type | Description |
| --- | --- | --- |
| `index` | string | Index name |
| `collection` | string | Collection name |
| `id` | string | The document id |
| `body` | json.RawMessage | A JSON string containing the body of the document |
| `options` | type.QueryOptions | A pointer to a `query_options` containing query options |


### Options

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `Queuable` | boolean | Make this request queuable or not | `true`  |
| `Refresh` | string | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) | `` |

## Return

Returns a JSON string containing the document creation result.

| Name | Type | Description
| --- | --- | ---
| _id | String | The id of the newly created document
| _version | int | The version of the document in the persistent data storage
| _source | object | The created document
| result | string | set to `created` in case of success and `updated` if the document already exists

## Usage

[snippet=create-or-replace]

---
layout: sdk.html.hbs
algolia: true
title: createOrReplace
description:
order: 200
---

# CreateOrReplace

Creates a new document in the persistent data storage, or replaces its content if it already exists.

The optional parameter `refresh` can be used with the value `wait_for` in order to wait for the document to be indexed (indexed documents are available for `search`).

## Arguments

```go
CreateOrReplace(
  index string, 
  collection string, 
  _id string, 
  body json.RawMessage, 
  options types.QueryOptions
) (json.RawMessage, error)
```

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>string</pre> | Index name |
| `collection` | <pre>string</pre> | Collection name |
| `id` | <pre>string</pre> | The document id |
| `body` | <pre>json.RawMessage</pre> | A JSON string containing the body of the document |
| `options` | <pre>types.QueryOptions</pre> | A struct containing query options |


### options

Additional query options

| Option | Type (default) | Description |
| --- | --- | --- |
| `Queuable` | <pre>bool</pre>  (`true`) | If true, queues the request during downtime, until connected to Kuzzle again |
| `Refresh` | <pre>string</pre> | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |

## Return

Returns a JSON string containing the document creation result.

| Name | Type | Description
| --- | --- | ---
| _id | String | The id of the newly created document
| _version | int | The version of the document in the persistent data storage
| _source | object | The created document
| result | <pre>string</pre> | set to `created` in case of success and `updated` if the document already exists

## Usage

[snippet=create-or-replace]

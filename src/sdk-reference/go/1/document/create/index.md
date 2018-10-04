---
layout: sdk.html
algolia: true
title: create
description:
order: 200
---

# create

Create a new document in Kuzzle

Returns an error if a document with the same given id already exists in Kuzzle.

The optional parameter `refresh` can be used with the value `wait_for` in order to wait for the document to be indexed (indexed documents are available for `search`).

## Signature

```go
Create(index string, collection string, id string, body json.RawMessage, options types.QueryOptions) (json.RawMessage, error) {
```

## Arguments

| Arguments | Type | Description | Required |
| --- | --- | --- | --- |
| `index` | string | Index name | yes |
| `collection` | string | Collection name | yes |
| `id` | string | Optional document id. If set to a blank string, will use a auto-generated id | yes |
| `body` | json.RawObject | The query to match | yes |
| `options` | types.Queryoptions | An object containing query options. | no |

###### **Options**

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `Queuable` | bool | Make this request queuable or not | `true`  |
| `Refresh` | string | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) | `` |

## Return

Returns a JSON string containing the created document.

## Usage

[snippet=create]

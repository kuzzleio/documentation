---
layout: sdk.html.hbs
algolia: true
title: create
description:
order: 200
---

# create

Create a new document in Kuzzle

Returns an error if a document with the same given id already exists in Kuzzle.

The optional parameter `refresh` can be used with the value `wait_for` in order to wait for the document to be indexed (indexed documents are available for `search`).


## Arguments

```go
Create(
  index string, 
  collection string, 
  id string, 
  body json.RawMessage, 
  options types.QueryOptions
) (json.RawMessage, error) {
```

| Arguments | Type | Description |
| --- | --- | --- |
| `index` | <pre>string</pre> | Index name |
| `collection` | <pre>string</pre> | Collection name |
| `id` | <pre>string</pre> | Optional document id. If set to a blank string, will use a auto-generated id |
| `body` | <pre>json.RawMessage</pre> | The query to match |
| `options` | <pre>types.QueryOptions</pre> | An struct containing query options |

### options

Additional query options

| Property   | Type    | Description                       |
| ---------- | ------- | --------------------------------- |
| `Queuable` | <pre>bool</pre> (`true`) | Make this request queuable or not |
| `Refresh` | <pre>string</pre> | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) | `` |

## Return

Returns a JSON string containing the created document.

## Usage

[snippet=create]

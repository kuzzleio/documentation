---
layout: sdk.html.hbs
algolia: true
title: replace
description:
order: 200
---

# Replace

Replaces the content of an existing document.

## Arguments

```go
Replace(index string, collection string, _id string, body json.RawMessage, options types.QueryOptions) (json.RawMessage, error)
```

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>string</pre> | Index name |
| `collection` | <pre>string</pre> | Collection name |
| `id` | <pre>string</pre> | The document id |
| `body` | <pre>string</pre> | A JSON string containing the body of the document |
| `options` | <pre>types.QueryOptions</pre> | A struct containing query options |

### options

Additional query options

| Option | Type (default) | Description |
| --- | --- | --- |
| `Queuable` | <pre>bool</pre>  (`true`) | If true, queues the request during downtime, until connected to Kuzzle again |
| `Refresh` | <pre>string</pre> | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |

## Return

Returns a JSON string containing the replaced document.

## Usage

[snippet=replace]

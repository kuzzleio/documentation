---
layout: sdk.html.hbs
algolia: true
title: <%= _.camelCase(action) %>
description:
order: 200
---

# <%= _.camelCase(action) %>

## Arguments

```go
<%= _.upperFirst(_.camelCase(action)) %>() error
```

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>string</pre> | Index name |
| `collection` | <pre>string</pre> | Collection name |
| `id` | <pre>string</pre> | The document id |
| `body` | <pre>string</pre> | A JSON string containing the body of the document |
| `options` | <pre>types.QueryOptions</pre> | A struct containing query options |

### options

| Options    | Type (default) | Description                       |
| ---------- | -------------- | --------------------------------- |
| `queuable` | <pre>bool</pre> (`true`) | If true, queues the request during downtime, until connected to Kuzzle again |
| `refresh` | <pre>string</pre> | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |

## Return

## Usage

[snippet=<%= _.kebabCase(action) %>]

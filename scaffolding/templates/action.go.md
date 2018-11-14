---
layout: sdk.html.hbs
algolia: true
title: <%= _.camelCase(action) %>
description:
---

# <%= _.camelCase(action) %>

## Arguments

```go
<%= _.upperFirst(_.camelCase(action)) %>() error
```

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| ``changeme`` | <pre>changme</pre> | changeme    |

### options

| Options    | Type (default) | Description                       |
| ---------- | -------------- | --------------------------------- |
| `queuable` | <pre>bool</pre> (`true`) | If true, queues the request during downtime, until connected to Kuzzle again |
| `refresh` | <pre>string</pre> | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |

## Return

## Usage

[snippet=<%= _.kebabCase(action) %>]

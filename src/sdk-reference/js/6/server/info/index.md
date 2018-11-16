---
layout: sdk.html.hbs
algolia: true
title: info
description: Returns information about Kuzzle server.
---

# info
{{{since "1.0.0"}}}

Returns information about Kuzzle: available API (base + extended), plugins, external services (Redis, Elasticsearch, ...), servers, etc.

## Arguments

```javascript
/**
* @param {Object} options - {queuable: Boolean(true)}
* @returns {Promise<Object>}
*/
info([options])
```

| Arguments | Type   | Description                         | Required |
| --------- | ------ | ----------------------------------- | -------- |
| `options` | Object | An object containing query options. | no       |

### **Options**

Additional query options

| Property   | Type   | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | If true, queues the request during downtime, until connected to Kuzzle again | `true`  |

## Resolve

Resolves to an `Object` containing server information.

## Usage

[snippet=info]

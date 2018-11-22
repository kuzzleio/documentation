---
layout: sdk.html.hbs
algolia: true
title: getConfig
description: Returns the current Kuzzle configuration.
algolia: true
---

# getConfig

Returns the current Kuzzle configuration.

<div class="alert alert-warning">
  This route should only be accessible to administrators, as it might return sensitive information about the backend.
</div>

## Arguments

```javascript
getConfig ([options])
```

<br/>

| Arguments | Type   | Description                         |
| --------- | ------ | ----------------------------------- |
| `options` | <pre>object</pre> | Query options |

### **Options**

Additional query options

| Property   | Type<br/>(default)   | Description                       |
| ---------- | ------- | --------------------------------- |
| `queuable` | <pre>boolean</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Resolve

Resolves to an `object` containing server configuration.

## Usage

[snippet=get-config]

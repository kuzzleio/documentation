---
layout: sdk.html.hbs
algolia: true
title: adminExists
description: Checks that an administrator account exists.
algolia: true
---

# adminExists

Checks that an administrator account exists.

## Arguments

```javascript
adminExists ([options])
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

Resolves to a `boolean` set to `true` if an admin exists and `false` if it does not.

## Usage

[snippet=admin-exists]

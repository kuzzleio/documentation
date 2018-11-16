---
layout: sdk.html.hbs
algolia: true
title: now
description: Returns the current server timestamp, in Epoch-millis format.
---

# now

{{{since "1.0.0"}}}

Returns the current server timestamp, in Epoch-millis format.

## Arguments

```javascript
/**
* @param {object} [options]
* @returns {Promise.<object>}
*/
now([options]);
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

Resolves to the current server timestamp as an `integer`.

## Usage

[snippet=now]

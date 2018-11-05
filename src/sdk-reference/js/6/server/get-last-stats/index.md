---
layout: sdk.html.hbs
algolia: true
title: getLastStats
description: Returns the most recent statistics snapshot.
order: 200
---

# getLastStats

{{{since "1.0.0"}}}

Returns the most recent statistics snapshot.

By default, snapshots are made every 10 seconds and they are stored for 1 hour.

These statistics include:

* the number of connected users per protocol (not available for all protocols)
* the number of ongoing requests
* the number of completed requests since the last frame
* the number of failed requests since the last frame
## Signature

```javascript
/**
 * @param {Object} options - {queuable: Boolean(true)}
 * @returns {Promise<Object>}
 */
getLastStats(options=null)
```

## Arguments

| Arguments | Type   | Description                         | Required |
| --------- | ------ | ----------------------------------- | -------- |
| `options` | Object | An object containing query options. | no       |

### **Options**

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |


## Resolve

Resolves to an `Object` containing the most recent statistics snapshot.

## Usage

[snippet=get-last-stats]

---
layout: sdk.html.hbs
algolia: true
title: getAllStats
description: Gets all stored internal statistic snapshots.
---

# getAllStats

{{{since "1.0.0"}}}

Gets all stored internal statistic snapshots.
By default, snapshots are made every 10 seconds and they are stored for 1 hour.

These statistics include:

* the number of connected users per protocol (not available for all protocols)
* the number of ongoing requests
* the number of completed requests since the last frame
* the number of failed requests since the last frame

## Arguments

```javascript
/**
* @param {Object} options - {queuable: Boolean(true)}
* @returns {Promise<Object>}
*/
getAllStats([options])
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

Resolves to an `Object` containing all stored internal statistic snapshots.

## Usage

[snippet=get-all-stats]

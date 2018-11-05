---
layout: sdk.html.hbs
algolia: true
title: getStats
description: Returns statistics snapshots within a provided timestamp range.
order: 200
---

# getStats

{{{since "1.0.0"}}}

Returns statistics snapshots within a provided timestamp range.
By default, snapshots are made every 10 seconds and they are stored for 1 hour.

These statistics include:

* the number of connected users per protocol (not available for all protocols)
* the number of ongoing requests
* the number of completed requests since the last frame
* the number of failed requests since the last frame

## Signature

```javascript
/**
 * @param {Number|String} startTime
 * @param {Number|String} stopTime
 * @param {Object} options - {queuable: Boolean(true)}
 * @returns {Promise<Object>}
 */
getStats(startTime, stopTime, options=null)
```

## Arguments

| Arguments | Type   | Description                         | Required |
| --------- | ------ | ----------------------------------- | -------- |
| `startTime` | Number, String | begining of statistics frame set (timestamp or datetime format) | yes       |
| `stopTime`  | Number, String | end of statistics frame set (timestamp or datetime format)      | yes       |
| `options`   | Object         | An object containing query options.                             | no        |

### **Options**

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |


## Resolve

Resolves to an `Object` containing statistics snapshots within the provided timestamp range.

## Usage

[snippet=get-stats]

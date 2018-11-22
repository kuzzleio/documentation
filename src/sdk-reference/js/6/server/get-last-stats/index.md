---
layout: sdk.html.hbs
algolia: true
title: getLastStats
description: Returns the most recent statistics snapshot.
algolia: true
---

# getLastStats

Returns the most recent statistics snapshot.

By default, snapshots are made every 10 seconds and they are stored for 1 hour.

These statistics include:

* the number of connected users per protocol (not available for all protocols)
* the number of ongoing requests
* the number of completed requests since the last frame
* the number of failed requests since the last frame

## Arguments

```javascript
getLastStats ([options])
```

<br/>

| Arguments | Type   | Description                         |
| --------- | ------ | ----------------------------------- |
| `options` | <pre>object</pre> | Query options | no       |

### **Options**

Additional query options

| Property   | Type<br/>(default)   | Description                       |
| ---------- | ------- | --------------------------------- |
| `queuable` | <pre>boolean</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |


## Resolve

Resolves to an `object` containing the most recent statistics snapshot.

## Usage

[snippet=get-last-stats]

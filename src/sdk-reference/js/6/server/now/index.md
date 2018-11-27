---
layout: sdk.html.hbs
title: now
description: Returns the current server timestamp, in Epoch-millis format.
---

# now

Returns the current server timestamp, in Epoch-millis format.

## Arguments

```javascript
now ([options]);
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

Resolves to the current server timestamp in Epoch-millis format.

## Usage

[snippet=now]

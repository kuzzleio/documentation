---
layout: sdk.html.hbs
algolia: true
title: now
description: Returns the current server timestamp, in Epoch-millis format.
order: 200
---

# now

{{{since "1.0.0"}}}

Returns the current server timestamp, in Epoch-millis format.

## Signature

```javascript
/**
 * @param {object} [options]
 * @returns {Promise.<object>}
 */
now(options = null);
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

Resolves to the current server timestamp as an `integer`.

## Usage

[snippet=now]

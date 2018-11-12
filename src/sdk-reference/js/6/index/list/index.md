---
layout: sdk.html.hbs
algolia: true
title: list
description: List indexes handled by kuzzle
order: 400
---

# list

Get the complete list of data indexes handled by Kuzzle.

## Arguments

```javascript
list (options = null);
```

<br/>

| Arguments | Type   | Description                         |
| --------- | ------ | ----------------------------------- |
| `options` | <pre>object</pre> | Query options |

### **options**

Additional query options

| Property     | Type    | Description   |
| -------------- | --------- | ------------- |
| `queuable` | <pre>boolean</pre> | Make this request queuable or not | `true`  |

## Resolve

Resolves to an `array<string>` containing the names of Kuzzle's indexes.

## Usage

[snippet=list]

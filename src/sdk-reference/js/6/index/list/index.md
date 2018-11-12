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
list ([options]);
```

<br/>

| Arguments | Type   | Description                         |
| --------- | ------ | ----------------------------------- |
| `options` | <pre>object</pre> | Query options |

### **options**

Additional query options

| Property     | Type<br/>(default)    | Description   |
| -------------- | --------- | ------------- |
|  `queuable`  |  <pre>boolean</pre> <br/>(`true`) |  Make this request queuable or not  |

## Resolve

Resolves to an `array<string>` containing the names of Kuzzle's indexes.

## Usage

[snippet=list]

---
layout: sdk.html.hbs
algolia: true
title: getStrategies
description: Get all authentication strategies registered in Kuzzle.
---

# getStrategies

Gets the exhaustive list of registered authentication strategies.

## Arguments

```javascript
getStrategies ([options])
```

<br/>

| Arguments    | Type    | Description
|--------------|---------|-------------
| `options` | <pre>object</pre> | Query options

### **options**

Additional query options

| Property     | Type    | Description                       | Default
| ---------- | ------- | --------------------------------- | -------
| `queuable` | <pre>boolean</pre><br/>(`true`) | Make this request queuable or not |

## Resolves

An `array<string of available strategies name.

## Usage

[snippet=get-strategies]

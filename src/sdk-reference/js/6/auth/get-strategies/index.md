---
layout: sdk.html.hbs
algolia: true
title: getStrategies
description: Get all authentication strategies registered in kuzzle.
---

# getStrategies

Gets the exhaustive list of registered authentication strategies.

## Arguments

```javascript
getStrategies (options = null)
```

<br/>

| Arguments    | Type    | Description
|--------------|---------|-------------
| `options` | <pre>object</pre> | Query options

### **options**

Additional query options

| Property     | Type    | Description                       | Default
| ---------- | ------- | --------------------------------- | -------
| `queuable` | <pre>boolean</pre> | Make this request queuable or not | `true`

## Resolve

An `array<string>` of available strategies name.

## Usage

[snippet=get-strategies]

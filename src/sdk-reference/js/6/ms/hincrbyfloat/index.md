---
layout: sdk.html.hbs
algolia: true
title: hincrbyfloat
---

# hincrbyfloat


Increments the number stored in a hash field by the provided float value.

[[_Redis documentation_]](https://redis.io/commands/hincrbyfloat)

## Arguments

```js
hincrbyfloat(key, [options])

```

<br/>

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| `key` | <pre>string</pre> | Key |
| ``options`` | <pre>object</pre> | Optional query arguments |

### options

The `options` arguments can contain the following option properties:

| Property   | Type (default)   | Description                       |
| ---------- | ------- | --------------------------------- |
| `queuable` | <pre>boolean (true)</pre> | If true, queues the request during downtime, until connected to Kuzzle again |

## Resolve

## Usage

[snippet=hincrbyfloat]

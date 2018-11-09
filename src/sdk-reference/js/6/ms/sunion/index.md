---
layout: sdk.html.hbs
algolia: true
title: sunion
---

# sunion


Returns the union of sets of unique values.

[[_Redis documentation_]](https://redis.io/commands/sunion)

## Arguments

```js
sunion(key, [options])

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

[snippet=sunion]

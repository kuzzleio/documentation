---
layout: sdk.html.hbs
algolia: true
title: zinterstore
---

# zinterstore


Computes the intersection of the provided sorted sets, and stores the result in a new sorted set.

[[_Redis documentation_]](https://redis.io/commands/zinterstore)

## Arguments

```js
zinterstore(key, [options])

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

[snippet=zinterstore]

---
layout: sdk.html.hbs
algolia: true
title: zcount
---

# zcount


Returns the number of elements held by a sorted set with a score within the provided `min` and `max` values.

[[_Redis documentation_]](https://redis.io/commands/zcount)

## Arguments

```js
zcount(key, [options])

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

[snippet=zcount]

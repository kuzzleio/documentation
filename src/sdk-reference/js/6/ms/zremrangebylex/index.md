---
layout: sdk.html.hbs
algolia: true
title: zremrangebylex
---

# zremrangebylex


Removes members within a provided range, from a sorted set where all elements have the same score, using lexicographical ordering. 

[[_Redis documentation_]](https://redis.io/commands/zremrangebylex)

## Arguments

```js
zremrangebylex(key, [options])

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

[snippet=zremrangebylex]

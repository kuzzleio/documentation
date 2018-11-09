---
layout: sdk.html.hbs
algolia: true
title: zrangebylex
---

# zrangebylex


Returns elements within a provided interval, in a sorted set where all members have equal score, using lexicographical ordering. 

[[_Redis documentation_]](https://redis.io/commands/zrangebylex)

## Arguments

```js
zrangebylex(key, [options])

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

[snippet=zrangebylex]

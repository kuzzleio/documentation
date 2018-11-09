---
layout: sdk.html.hbs
algolia: true
title: zrevrangebylex
---

# zrevrangebylex


Identical to [zrangebylex]({{ site_base_path }}api/1/controller-memory-storage/zrangebylex) except that the sorted set is traversed in descending order.

[[_Redis documentation_]](https://redis.io/commands/zrevrangebylex)

## Arguments

```js
zrevrangebylex(key, [options])

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

[snippet=zrevrangebylex]

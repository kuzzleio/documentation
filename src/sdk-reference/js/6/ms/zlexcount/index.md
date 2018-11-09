---
layout: sdk.html.hbs
algolia: true
title: zlexcount
---

# zlexcount


Counts elements in a sorted set where all members have equal score, using lexicographical ordering. 

[[_Redis documentation_]](https://redis.io/commands/zlexcount)

## Arguments

```js
zlexcount(key, [options])

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

[snippet=zlexcount]

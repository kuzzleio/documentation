---
layout: sdk.html.hbs
algolia: true
title: zrank
---

# zrank


Returns the position of an element in a sorted set, with scores sorted in ascending order. The index returned is 0-based (the lowest score member has an index of 0).

[[_Redis documentation_]](https://redis.io/commands/zrank)

## Arguments

```js
zrank(key, [options])

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

[snippet=zrank]

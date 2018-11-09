---
layout: sdk.html.hbs
algolia: true
title: lrange
---

# lrange


Returns the list elements between the `start` and `stop` positions.

Offsets start at `0`, and the range is inclusive.

[[_Redis documentation_]](https://redis.io/commands/lrange)


## Arguments

```js
lrange(key, [options])

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

[snippet=lrange]

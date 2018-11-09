---
layout: sdk.html.hbs
algolia: true
title: pfmerge
---

# pfmerge


Merges multiple [HyperLogLog](https://en.wikipedia.org/wiki/HyperLogLog) data structures into an unique HyperLogLog structure stored at `_id`, approximating the cardinality of the union of the source structures.

[[_Redis documentation_]](https://redis.io/commands/pfmerge)

## Arguments

```js
pfmerge(key, [options])

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

[snippet=pfmerge]

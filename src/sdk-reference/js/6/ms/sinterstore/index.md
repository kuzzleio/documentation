---
layout: sdk.html.hbs
algolia: true
title: sinterstore
---

# sinterstore


Computes the intersection of the provided sets of unique values, and stores the result in a destination key.

If the destination key already exists, it is overwritten.

[[_Redis documentation_]](https://redis.io/commands/sinterstore)

## Arguments

```js
sinterstore(key, [options])

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

[snippet=sinterstore]

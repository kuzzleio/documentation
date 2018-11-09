---
layout: sdk.html.hbs
algolia: true
title: setnx
---

# setnx


Sets a value on a key, only if it does not already exist.

[[_Redis documentation_]](https://redis.io/commands/setnx)

## Arguments

```js
hsetnx(key, [options])
msetnx(key, [options])
setnx(key, [options])

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

[snippet=setnx]

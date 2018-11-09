---
layout: sdk.html.hbs
algolia: true
title: lpush
---

# lpush


Prepends the specified values to a list. 

If the key does not exist, it is created holding an empty list before performing the operation.

[[_Redis documentation_]](https://redis.io/commands/lpush)

## Arguments

```js
lpush(key, [options])
rpoplpush(key, [options])

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

[snippet=lpush]

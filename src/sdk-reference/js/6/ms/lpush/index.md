---
layout: sdk.html.hbs
title: lpush
---

# lpush

Prepends the specified values to a list. 

If the key does not exist, it is created holding an empty list before performing the operation.

[[_Redis documentation_]](https://redis.io/commands/lpush)

## Signature

```js
lpush(key, values, [options])
```

## Arguments

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| `key` | <pre>string</pre> | List key |
| `values` | <pre>string[]</pre> | Values to append |
| ``options`` | <pre>object</pre> | Optional query arguments |

### options

The `options` arguments can contain the following option properties:

| Property   | Type (default)   | Description                       |
| ---------- | ------- | --------------------------------- |
| `queuable` | <pre>boolean (true)</pre> | If true, queues the request during downtime, until connected to Kuzzle again |

## Resolve

Resolves to the updated length of the list.

## Usage

[snippet=lpush]

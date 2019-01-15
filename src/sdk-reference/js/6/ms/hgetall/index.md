---
layout: sdk.html.hbs
title: hgetall
---

# hgetall


Returns all fields and values of a hash.

[[_Redis documentation_]](https://redis.io/commands/hgetall)

## Signature

```js
hgetall(key, [options])
```

## Arguments

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| `key` | <pre>string</pre> | Hash key |
| ``options`` | <pre>object</pre> | Optional query arguments |

### options

The `options` arguments can contain the following option properties:

| Property   | Type (default)   | Description                       |
| ---------- | ------- | --------------------------------- |
| `queuable` | <pre>boolean (true)</pre> | If true, queues the request during downtime, until connected to Kuzzle again |

## Resolve

Resolves to an object representation of the hash.

## Usage

[snippet=hgetall]

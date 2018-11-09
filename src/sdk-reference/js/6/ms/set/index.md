---
layout: sdk.html.hbs
algolia: true
title: set
---

# set


Creates a key holding the provided value, or overwrites it if it already exists.

[[_Redis documentation_]](https://redis.io/commands/set)

## Arguments

```js
getset(key, [options])
hmset(key, [options])
hset(key, [options])
lset(key, [options])
mset(key, [options])
set(key, [options])

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

[snippet=set]

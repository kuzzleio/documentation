---
layout: sdk.html.hbs
algolia: true
title: hset
---

# hset


Sets a field and its value in a hash. 

If the key does not exist, a new key holding a hash is created. 

If the field already exists, its value is overwritten.

[[_Redis documentation_]](https://redis.io/commands/hset)

## Arguments

```js
hset(key, [options])

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

[snippet=hset]

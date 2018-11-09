---
layout: sdk.html.hbs
algolia: true
title: sadd
---

# sadd


Adds members to a set of unique values stored at `key`. 

If the destination set does not exist, it is created beforehand.

[[_Redis documentation_]](https://redis.io/commands/sadd)

## Arguments

```js
sadd(key, [options])

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

[snippet=sadd]

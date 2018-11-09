---
layout: sdk.html.hbs
algolia: true
title: zincrby
---

# zincrby


Increments the score of a sorted set member by the provided value.

[[_Redis documentation_]](https://redis.io/commands/zincrby)

## Arguments

```js
zincrby(key, [options])

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

[snippet=zincrby]

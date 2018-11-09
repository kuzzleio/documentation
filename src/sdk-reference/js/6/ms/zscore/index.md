---
layout: sdk.html.hbs
algolia: true
title: zscore
---

# zscore


Returns the score of an element in a sorted set.

[[_Redis documentation_]](https://redis.io/commands/zscore)

## Arguments

```js
zscore(key, [options])

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

[snippet=zscore]

---
layout: sdk.html.hbs
algolia: true
title: spop
---

# spop


Removes and returns one or more elements at random from a set of unique values. If multiple elements are removed, the result set will be an array of removed elements, instead of a string.

[[_Redis documentation_]](https://redis.io/commands/spop)

## Arguments

```js
spop(key, [options])

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

[snippet=spop]

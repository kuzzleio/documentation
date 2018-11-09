---
layout: sdk.html.hbs
algolia: true
title: lrem
---

# lrem


Removes the first occurences of an element from a list.

[[_Redis documentation_]](https://redis.io/commands/lrem)

## Arguments

```js
lrem(key, [options])

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

[snippet=lrem]

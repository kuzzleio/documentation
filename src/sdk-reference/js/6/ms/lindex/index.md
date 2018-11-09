---
layout: sdk.html.hbs
algolia: true
title: lindex
---

# lindex


Returns the element at the provided index in a list.

[[_Redis documentation_]](https://redis.io/commands/lindex)

## Arguments

```js
lindex(key, [options])

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

[snippet=lindex]

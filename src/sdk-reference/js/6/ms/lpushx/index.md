---
layout: sdk.html.hbs
algolia: true
title: lpushx
---

# lpushx


Prepends the specified value to a list, only if the key already exists and if it holds a list.

[[_Redis documentation_]](https://redis.io/commands/lpushx)

## Arguments

```js
lpushx(key, [options])

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

[snippet=lpushx]

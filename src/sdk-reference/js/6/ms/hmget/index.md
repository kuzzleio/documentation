---
layout: sdk.html.hbs
algolia: true
title: hmget
---

# hmget


Returns the values of the specified hash's fields.

[[_Redis documentation_]](https://redis.io/commands/hmget)

## Arguments

```js
hmget(key, [options])

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

[snippet=hmget]

---
layout: sdk.html.hbs
algolia: true
title: sismember
---

# sismember


Checks if a value is a member of a set of unique values.

[[_Redis documentation_]](https://redis.io/commands/sismember)

## Arguments

```js
sismember(key, [options])

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

[snippet=sismember]

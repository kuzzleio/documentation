---
layout: sdk.html.hbs
algolia: true
title: keys
---

# keys


Returns all keys matching the provided pattern.

[[_Redis documentation_]](https://redis.io/commands/keys)

## Arguments

```js
hkeys(key, [options])
keys(key, [options])

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

[snippet=keys]

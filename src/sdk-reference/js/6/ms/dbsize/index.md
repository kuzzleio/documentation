---
layout: sdk.html.hbs
title: dbsize
---

# dbsize


Returns the number of keys in the application database.

[[_Redis documentation_]](https://redis.io/commands/dbsize)

## Signature

```js
dbsize([options])

```

## Arguments

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| ``options`` | <pre>object</pre> | Optional query arguments |

### options

The `options` arguments can contain the following option properties:

| Property   | Type (default)   | Description                       |
| ---------- | ------- | --------------------------------- |
| `queuable` | <pre>boolean (true)</pre> | If true, queues the request during downtime, until connected to Kuzzle again |

## Resolve

Resolves to the number of found keys.

## Usage

[snippet=dbsize]

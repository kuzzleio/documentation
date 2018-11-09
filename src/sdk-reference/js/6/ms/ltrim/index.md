---
layout: sdk.html.hbs
algolia: true
title: ltrim
---

# ltrim


Trims an existing list so that it will contain only the specified range of elements specified.

[[_Redis documentation_]](https://redis.io/commands/ltrim)

## Arguments

```js
ltrim(key, [options])

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

[snippet=ltrim]

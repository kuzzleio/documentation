---
layout: sdk.html.hbs
algolia: true
title: touch
---

# touch


Alters the last access time of one or multiple keys. A key is ignored if it does not exist.

[[_Redis documentation_]](https://redis.io/commands/touch)

## Arguments

```js
touch(key, [options])

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

[snippet=touch]

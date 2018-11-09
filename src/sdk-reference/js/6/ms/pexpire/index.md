---
layout: sdk.html.hbs
algolia: true
title: pexpire
---

# pexpire


Sets a timeout (in milliseconds) on a key. After the timeout has expired, the key will automatically be deleted.

[[_Redis documentation_]](https://redis.io/commands/pexpire)

## Arguments

```js
pexpire(key, [options])

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

[snippet=pexpire]

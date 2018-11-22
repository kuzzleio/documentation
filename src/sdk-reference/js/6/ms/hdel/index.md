---
layout: sdk.html.hbs
algolia: true
title: hdel
---

# hdel

Removes fields from a hash.

[[_Redis documentation_]](https://redis.io/commands/hdel)

## Arguments

```js
hdel(key, fields, [options])
```

<br/>

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| `key` | <pre>string</pre> | Hash key |
| `fields` | <pre>string[]</pre> | Field keys to delete |
| ``options`` | <pre>object</pre> | Optional query arguments |

### options

The `options` arguments can contain the following option properties:

| Property   | Type (default)   | Description                       |
| ---------- | ------- | --------------------------------- |
| `queuable` | <pre>boolean (true)</pre> | If true, queues the request during downtime, until connected to Kuzzle again |

## Resolve

Resolves to the number of deleted fields.

## Usage

[snippet=hdel]
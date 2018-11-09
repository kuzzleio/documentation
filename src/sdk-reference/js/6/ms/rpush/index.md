---
layout: sdk.html.hbs
algolia: true
title: rpush
---

# rpush


Appends values at the end of a list. 

If the destination list does not exist, it is created holding an empty list before performing the operation.

[[_Redis documentation_]](https://redis.io/commands/rpush)


## Arguments

```js
rpush(key, [options])

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

[snippet=rpush]

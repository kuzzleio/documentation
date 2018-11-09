---
layout: sdk.html.hbs
algolia: true
title: sort
---

# sort


Sorts and returns elements contained in a list, a set of unique values or a sorted set.  
By default, sorting is numeric and elements are compared by their value, interpreted as double precision floating point number.

[[_Redis documentation_]](https://redis.io/commands/sort)

## Arguments

```js
sort(key, [options])

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

[snippet=sort]

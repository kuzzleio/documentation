---
layout: sdk.html.hbs
algolia: true
title: zadd
description:
---

# zadd


Adds elements to a sorted set. 

If the key does not exist, it is created, holding an empty sorted set. 

If the key already exists but does not hold a sorted set, an error is returned.

If a member to insert is already in the sorted set, its score is updated and the member is reinserted at the right position in the set.

[[_Redis documentation_]](https://redis.io/commands/zadd)

## Arguments

```js
zadd (...args) {}

```

<br/>

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| ``changeme`` | <pre>changme</pre> | changeme    |

### arg1

### arg2

## Resolve

## Usage

[snippet=zadd]

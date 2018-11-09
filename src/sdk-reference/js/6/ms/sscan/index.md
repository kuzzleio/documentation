---
layout: sdk.html.hbs
algolia: true
title: sscan
description:
---

# sscan


Iterates incrementally over members contained in a set of unique values, using a cursor.

An iteration starts when the cursor is set to 0.  
To get the next page of results, simply re-send the request with the updated cursor position provided in the result set.  

The scan ends when the cursor returned by the server is 0.

[[_Redis documentation_]](https://redis.io/commands/sscan)

## Arguments

```js
sscan (...args) {}

```

<br/>

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| ``changeme`` | <pre>changme</pre> | changeme    |

### arg1

### arg2

## Resolve

## Usage

[snippet=sscan]

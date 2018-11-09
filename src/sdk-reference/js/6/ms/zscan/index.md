---
layout: sdk.html.hbs
algolia: true
title: zscan
description:
---

# zscan
layout: full.html.hbs
algolia: true
title: zscan
---

# zscan

{{{since "1.0.0"}}}

Iterates incrementally over members contained in a sorted set, using a cursor.

An iteration starts when the cursor is set to 0.  
To get the next page of results, simply re-send the request with the updated cursor position provided in the result set.  

The scan ends when the cursor returned by the server is 0.

[[_Redis documentation_]](https://redis.io/commands/sscan)

---

## Arguments

```js
zscan (...args) {}

```

<br/>

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| ``changeme`` | <pre>changme</pre> | changeme    |

### arg1

### arg2

## Resolve

## Usage

[snippet=zscan]

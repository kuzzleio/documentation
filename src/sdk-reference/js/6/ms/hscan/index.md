---
layout: sdk.html.hbs
algolia: true
title: hscan
description:
---

# hscan
layout: full.html.hbs
algolia: true
title: hscan
---

# hscan

{{{since "1.0.0"}}}

Iterates incrementally over fields contained in a hash, using a cursor.

An iteration starts when the cursor is set to 0.  
To get the next page of results, simply re-send the request with the updated cursor position provided in the result set.  

The scan ends when the cursor returned by the server is 0.

[[_Redis documentation_]](https://redis.io/commands/hscan)

---

## Arguments

```js
hscan (...args) {}

```

<br/>

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| ``changeme`` | <pre>changme</pre> | changeme    |

### arg1

### arg2

## Resolve

## Usage

[snippet=hscan]

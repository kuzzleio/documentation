---
layout: sdk.html.hbs
algolia: true
title: scan
description:
---

# scan
layout: full.html.hbs
algolia: true
title: scan
---

# scan

{{{since "1.0.0"}}}

Iterates incrementally over the set of keys in the database using a cursor.

An iteration starts when the cursor is set to 0.  
To get the next page of results, simply re-send the request with the updated cursor position provided in the result set.

The scan ends when the cursor returned by the server is 0.

[[_Redis documentation_]](https://redis.io/commands/scan)

---

## Arguments

```js
hscan (...args) {}
scan (...args) {}
sscan (...args) {}
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

[snippet=scan]

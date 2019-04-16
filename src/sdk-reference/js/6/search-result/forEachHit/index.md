---
layout: sdk.html.hbs
title: forEachHit
description: Executes an action on every retrieved documents
---

# SearchResult

Iterates through all pages of results and executes an action for every document.

## Arguments

```js
forEachHit(action);
```

<br/>

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| ``action`` | <pre>Function</pre> | Action to execute |

**Note:**
 * the callback function take a hit as only argument
 * if the callback function returns a promise, they will be executed in parallel and the method will wait for their resolution

[snippet=foreachhit]

---
layout: full.html.hbs
title: InternalError
---

# InternalError

{{{since "1.0.0"}}}

Unexpected error. Should be reserved for Kuzzle's use only.

## Status Code

`500`

## Example

```js
const err = new context.errors.InternalError('error message');
```

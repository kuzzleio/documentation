---
layout: full.html.hbs
algolia: true
title: SizeLimitError
---


# SizeLimitError

{{{since "1.0.0"}}}

Request exceeds the maximum limits.

## Status Code

`413`

## Example

```js
const err = new context.errors.SizeLimitError('error message');
```

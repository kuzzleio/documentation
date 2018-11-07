---
layout: full.html.hbs
algolia: true
title: BadRequestError
---

# BadRequestError

{{{since "1.0.0"}}}

Invalid request syntax.

## Status code

`400`

## Example

```js
const err = new context.errors.BadRequestError('error message');
```

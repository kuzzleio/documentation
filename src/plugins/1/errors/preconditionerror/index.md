---
layout: full.html.hbs
algolia: true
title: PreconditionError
---


# PreconditionError

{{{since "1.0.0"}}}

Unmet request prerequisites.

## Status Code

`412`

## Example

```js
const err = new context.errors.PreconditionError('error message');
```

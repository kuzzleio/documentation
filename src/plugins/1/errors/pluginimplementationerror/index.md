---
layout: full.html.hbs
title: PluginImplementationError
---

# PluginImplementationError

{{{since "1.0.0"}}}

Unexpected plugin failure.

## Status Code

`500`

## Example

```js
const err = new context.errors.PluginImplementationError('error message');
```

---
layout: full.html.hbs
algolia: true
title: ParseError
---


# ParseError 

{{{since "1.0.0"}}} / {{{deprecated "1.4.1"}}}

Parse error. Use [BadRequestError]({{ site_base_path }}plugins/1/errors/badrequesterror) instead.

## Status Code

`400`

## Example

```js
const err = new context.errors.ParseError('error message');
```

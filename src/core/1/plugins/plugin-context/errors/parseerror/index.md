---
code: true
type: page
title: ParseError
---

# ParseError

 / <DeprecatedBadge version="1.4.1" />

Parse error. Use [BadRequestError](/core/1/plugins/errors/badrequesterror) instead.

## Status Code

`400`

## Example

```js
const err = new context.errors.ParseError('error message');
```

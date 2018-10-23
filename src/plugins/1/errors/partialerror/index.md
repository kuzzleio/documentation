---
layout: full.html.hbs
algolia: true
title: PartialError
---

# PartialError

{{{since "1.0.0"}}}

Partial request success.

## Constructor

`new context.error.PartialError(message, errors)`

* `message`: {string} error message
* `failures`: {[KuzzleError]({{ site_base_path }}plugins/1/errors/kuzzleerror)[]} list of encountered errors

## Status Code

`206`

## Example

```js
const err = new context.errors.PartialError('error message', [
  new context.errors.BadRequestError('[request part] invalid format'),
  new context.errors.ForbiddenError('[other request part] forbidden')
]);
```

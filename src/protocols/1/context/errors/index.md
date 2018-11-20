---
layout: full.html.hbs
algolia: true
title: errors
---


# errors

The `context.errors` object regroups all error objects, used in request responses, or to be used by the protocol if needs be.


## BadRequestError

{{{since "1.0.0"}}}

Invalid request syntax.

### Status code

`400`

### Example

```js
const err = new context.errors.BadRequestError('error message');
```


## ForbiddenError

{{{since "1.0.0"}}}

Unauthorized access to a resource.

### Status Code

`403`

### Example

```js
const err = new context.errors.ForbiddenError('error message');
```


## InternalError

{{{since "1.0.0"}}}

Unexpected error. Should be reserved for Kuzzle's use only.

### Status Code

`500`

### Example

```js
const err = new context.errors.InternalError('error message');
```


## ParseError 

{{{since "1.0.0"}}} / {{{deprecated "1.4.1"}}}

Parse error. Use [BadRequestError]({{ site_base_path }}protocols/1/context/errors/#badrequesterror-default) instead.

### Status Code

`400`

### Example

```js
const err = new context.errors.ParseError('error message');
```


## PluginImplementationError

{{{since "1.0.0"}}}

Unexpected plugin failure.

### Status Code

`500`

### Example

```js
const err = new context.errors.PluginImplementationError('error message');
```


## ServiceUnavailableError

{{{since "1.0.0"}}}

Temporarily unable to respond.

### Status Code

`503`

### Example

```js
const err = new context.errors.ServiceUnavailableError('error message');
```


## UnauthorizedError

{{{since "1.0.0"}}}

Authentication failed.

### Status Code

`401`

### Example 

```js
const err = new context.errors.UnauthorizedError('error message');
```

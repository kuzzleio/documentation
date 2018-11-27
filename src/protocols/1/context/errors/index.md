---
layout: full.html.hbs
algolia: true
title: errors
algolia: true
---

# errors

The `context.errors` object regroups all error objects, used in request responses, or to be used by the protocol if needs be.

---

## KuzzleError

{{{since "1.0.0"}}}

Inherits from the standard Javascript `Error` object: abstract class inherited by all Kuzzle error objects.

This class should only be used to create new Kuzzle error objects.

### Properties

| Properties | Type | Description |
|-----------|------|-------------|
| `message` | <pre>string</pre> | Error message |
| `stack` | <pre>string[]</pre> | Error stack trace (not available in production mode) |
| `status` | <pre>integer</pre> | Error status code, following the standard [HTTP status code](https://en.wikipedia.org/wiki/List_of_HTTP_status_codes)

---

## BadRequestError

{{{since "1.0.0"}}}

Invalid request syntax.

### Status code

`400`

### Example

```js
const err = new context.errors.BadRequestError('error message');
```

---

## ExternalServiceError

{{{since "1.0.0"}}}

External service failure.

### Status Code

`500`

### Example

```js
const err = new context.errors.ExternalServiceError('error message');
```

---

## ForbiddenError

{{{since "1.0.0"}}}

Unauthorized access to a resource.

### Status Code

`403`

### Example

```js
const err = new context.errors.ForbiddenError('error message');
```

---

## GatewayTimeoutError

{{{since "1.0.0"}}}

Timeout error.

### Status code

`504`

### Example

```js
const err = new context.errors.GatewayTimeoutError('error message');
```

---

## InternalError

{{{since "1.0.0"}}}

Unexpected error. Should be reserved for Kuzzle's use only.

### Status Code

`500`

### Example

```js
const err = new context.errors.InternalError('error message');
```

---

## NotFoundError

{{{since "1.0.0"}}}

Resource not found.

### Status Code

`404`

### Example

```js
const err = new context.errors.NotFoundError('error message');
```

---

## ParseError 

{{{since "1.0.0"}}} / {{{deprecated "1.4.1"}}}

Parse error. Use [BadRequestError]({{ site_base_path }}protocols/1/context/errors/#badrequesterror-default) instead.

### Status Code

`400`

### Example

```js
const err = new context.errors.ParseError('error message');
```

---

## PartialError

{{{since "1.0.0"}}}

Partial request success.

### Constructor

```js
new context.error.PartialError(message, errors)
```

<br/>

| Arguments | Type | Description |
|-----------|------|-------------|
| `message` | <pre>string</pre> | Error message |
| `failures` | <pre><a href={{ site_base_path }}protocols/1/context/errors/#kuzzleerror-default>KuzzleError[]</a></pre> | List of encountered errors |

### Status Code

`206`

### Example

```js
const err = new context.errors.PartialError('error message', [
  new context.errors.BadRequestError('[request part] invalid format'),
  new context.errors.ForbiddenError('[other request part] forbidden')
]);
```

---

## PluginImplementationError

{{{since "1.0.0"}}}

Unexpected plugin failure.

### Status Code

`500`

### Example

```js
const err = new context.errors.PluginImplementationError('error message');
```

---

## PreconditionError

{{{since "1.0.0"}}}

Unmet request prerequisites.

### Status Code

`412`

### Example

```js
const err = new context.errors.PreconditionError('error message');
```

---

## ServiceUnavailableError

{{{since "1.0.0"}}}

Temporarily unable to respond.

### Status Code

`503`

### Example

```js
const err = new context.errors.ServiceUnavailableError('error message');
```

---

## SizeLimitError

{{{since "1.0.0"}}}

Request exceeds the maximum limits.

### Status Code

`413`

### Example

```js
const err = new context.errors.SizeLimitError('error message');
```

---

## UnauthorizedError

{{{since "1.0.0"}}}

Authentication failed.

### Status Code

`401`

### Example 

```js
const err = new context.errors.UnauthorizedError('error message');
```

---
layout: full.html.hbs
algolia: true
title: Error Handling
description: Understanding the Kuzzle error handling mechanisms
order: 500
---

# Error Handling

All errors received by Kuzzle clients are `KuzzleError` error objects.

A `KuzzleError` object has the following properties:

| property | type | description |
| -------- | ---- | ----------- |
| `status` | integer | HTTP status code |
| `message` | text | Short description of the error |
| `stack` | text | (Available in development mode only) Error stack trace |

Clients can detect the error type based on the `status` and process the error accordingly.

---

## Common errors

All Kuzzle requests can return one of the following errors:

### BadRequestError

{{{since "1.0.0"}}}

**status**: 400

A `BadRequestError` error is thrown if Kuzzle was unable to process the action due to a malformed request, or if required parameters are missing.

---

### ExternalServiceError

{{{since "1.0.0"}}}

**status**: 500

An `ExternalServiceError` error is thrown if Kuzzle was unable to process the action due to an external service failure (e.g. database down).

---

### ForbiddenError

{{{since "1.0.0"}}}

**status**: 403

A `ForbiddenError` error is thrown if the current authenticated user is not authorized to perform the requested action.

---

### GatewayTimeoutError

{{{since "1.0.0"}}}

**status**: 504

A `GatewayTimeoutError` error is thrown if Kuzzle, or a plugin, takes too long to respond.

Receiving this error does not guarantee the original request was not processed, just that it was not processed _in time_.

The Client Application will have to determine if the process was completed.

---

### InternalError

{{{since "1.0.0"}}}

**status**: 500

An `InternalError` error is thrown if Kuzzle encountered an unexpected error.

---

### PluginImplementationError

{{{since "1.0.0"}}}

**status**: 500

A `PluginImplementationError` error is a generic error thrown by Kuzzle on a [plugin]({{ site_base_path }}plugins/1) failure.

---

### ServiceUnavailableError

{{{since "1.0.0"}}}

**status**: 503

A `ServiceUnavailableError` error can be sent by Kuzzle if it overloaded and cannot temporarily accept new requests, or if the requested Kuzzle instance is shutting down.

---

## Specific errors

These errors are specific to controller actions.  
Check controllers documentation.

### NotFoundError

{{{since "1.0.0"}}}

**status**: 404

A `NotFoundError` error is thrown if the requested resource could not be found (e.g. a document is requested with a non-existing id).

---

### PartialError

{{{since "1.0.0"}}}

**status**: 206

A `PartialError` error is thrown if Kuzzle was unable to process a subset of a multi-action request.

A `PartialError` can be triggered, for instance, if one or several queries inside a `document:mCreate` request failed.

The detail of each failure can be retrieved in the `errors` property of the error object.

### Additional Properties

| property | type | description |
| -------- | ---- | ----------- |
| `count` | integer | Number of failures encountered |
| `errors` |  array of objects | Failed actions |

---

### PreconditionError

{{{since "1.0.0"}}}

**status**: 412

A `PreconditionError` error is thrown if Kuzzle was not able to process the request due to an invalid state.

For instance, this error can be generated when trying to create a document on a non-existing data index.

---

### SizeLimitError

{{{since "1.0.0"}}}

**status**: 413

A `SizeLimitError` error is thrown by Kuzzle if the request size exceeds the limits defined in the [configuration]({{ site_base_path }}guide/essentials/configuration).

---

### UnauthorizedError

{{{since "1.0.0"}}}

**status**: 401

An `UnauthorizedError` error is thrown by Kuzzle when an authentication attempt failed, or if a requested resource needs an authentication to be accessed.

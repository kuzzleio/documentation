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
| 
## Common errors

All Kuzzle requests can return one of the following errors:

### BadRequestError

{{{since "1.0.0"}}}

**status**: 400

A `BadRequestError` error is thrown if Kuzzle was unable to process the action due to a malformed request, or if required parameters are missing.


### ForbiddenError

{{{since "1.0.0"}}}

**status**: 403

A `ForbiddenError` error is thrown if the current authenticated user is not authorized to perform the requested action.


### InternalError

{{{since "1.0.0"}}}

**status**: 500

An `InternalError` error is thrown if Kuzzle encountered an unexpected error.


### ServiceUnavailableError

{{{since "1.0.0"}}}

**status**: 503

A `ServiceUnavailableError` error can be sent by Kuzzle if it overloaded and cannot temporarily accept new requests, or if the requested Kuzzle instance is shutting down.


### PartialError

{{{since "1.0.0"}}}

**status**: 206

A `PartialError` error is thrown if Kuzzle was unable to process a subset of a multi-action request.

A `PartialError` can be triggered, for instance, if one or several queries inside a `document:mCreate` request failed.

The detail of each failure can be retrieved in the `errors` property of the error object.

### Additional Properties

| property | type | description |
| 
### PreconditionError

{{{since "1.0.0"}}}

**status**: 412

A `PreconditionError` error is thrown if Kuzzle was not able to process the request due to an invalid state.

For instance, this error can be generated when trying to create a document on a non-existing data index.


### UnauthorizedError

{{{since "1.0.0"}}}

**status**: 401

An `UnauthorizedError` error is thrown by Kuzzle when an authentication attempt failed, or if a requested resource needs an authentication to be accessed.

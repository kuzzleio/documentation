---
code: false
type: page
title: Error Handling
description: How to handle errors with the SDK
order: 100
---

# Error Handling

SDK methods handle failure by throwing exceptions inheriting the `kuzzleio::KuzzleException` class, which in turn inherits the standard `std::runtime_error` class.

Members:

- `unsigned int status`: error code, following [HTTP Standards](https://en.wikipedia.org/wiki/List_of_HTTP_status_codes)
- `const char * what() const;`: returns the error message

You can find a detailed list of possible errors messages and statuses in the [documentation API](/core/1/api/essentials/errors).  
Just replace _Error_ by _Exception_ to find the exception name. (e.g. `BadRequestError` becomes `kuzzleio::BadRequestException`).

#### Example

<<< ./snippets/error-handling.cpp

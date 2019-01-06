---
layout: sdk.html.hbs
title: Error Handling
description: How to handle errors with the SDK
order: 110
---

# Error Handling

SDK methods handle failure by throwing exceptions inheriting the `Kuzzleio::KuzzleException` class, which in turn inherits the standard `System.ApplicationException` class.

Members:
* `int status`: error code, following [HTTP Standards](https://en.wikipedia.org/wiki/List_of_HTTP_status_codes)
* `string getMessage();`: returns the error message

You can find a detailed list of possible errors messages and statuses in the [documentation API]({{ site_base_path }}api/1/essentials/errors).  
Just replace *Error* by *Exception* to find the exception name. (e.g. `BadRequestError` becomes `Kuzzleio::BadRequestException`).

#### Example
[snippet=error-handling]

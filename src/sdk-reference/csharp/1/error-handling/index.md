---
layout: sdk.html.hbs
title: Error Handling
description: How to handle errors with the SDK
order: 100
separator: essentials
---

# Error Handling

SDK methods handle failure by throwing exceptions inheriting the `KuzzleException` class, which in turn inherits the standard `System.Exception` class.

Members:
* `int status`: error code, following [HTTP Standards](https://en.wikipedia.org/wiki/List_of_HTTP_status_codes)
* `string what() ;`: returns the error message

You can find a detailed list of possible errors messages and statuses in the [documentation API]({{ site_base_path }}api/1/errors).  
Just replace *Error* by *Exception* to find the exception name. (e.g. `BadRequestError` becomes `BadRequestException`).

#### Example
[snippet=error-handling]

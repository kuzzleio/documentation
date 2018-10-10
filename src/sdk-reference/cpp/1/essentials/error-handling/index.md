---
layout: sdk.html.hbs
algolia: true
title: Error Handling
description: How to handle errors with the SDK
order: 100
---

# Error Handling

Each of the SDK methods is likely to fail and throw an exception.  

Exceptions are subclasses of the `KuzzleException` class.  

A `KuzzleException` contain two informations:
  - `status` : Status code following [HTTP Standards](https://en.wikipedia.org/wiki/List_of_HTTP_status_codes)
  - `message` : Message describing the error

The `status` is a readable property on the exception and the `message` can be accessed with `getMessage()` getter.

You can find a detailed list of possible errors messages and statuses in the [documentation API]({{ site_base_path }}api/1/errors).  
Just replace *Error* by *Exception* to find the exception name. (e.g. `BadRequestError` becomes `BadRequestException`).

#### Example
[snippet=error-handling]

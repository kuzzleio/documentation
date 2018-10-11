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

You can access the error message with the `getMessage()` method.

You can find a detailed list of possible errors messages and statuses in the [documentation API]({{ site_base_path }}api/1/errors).  
Just replace *Error* by *Exception* to find the exception name. (e.g. `BadRequestError` becomes `BadRequestException`).

#### Example
[snippet=error-handling]

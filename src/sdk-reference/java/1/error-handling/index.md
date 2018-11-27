---
layout: sdk.html.hbs
algolia: true
title: Error Handling
description: How to handle errors with the SDK
order: 100
separator: essentials
algolia: true
---

# Error Handling

SDK methods handle failure by throwing exceptions inheriting the `KuzzleException` class, which is a standard Java exception extending the `Throwable` class.

You can find a detailed list of possible errors messages and statuses in the [documentation API]({{ site_base_path }}api/1/errors).  
Just replace *Error* by *Exception* to find the exception name. (e.g. `BadRequestError` becomes `BadRequestException`).

#### Example
[snippet=error-handling]

---
code: false
type: page
title: Error Handling
description: How to handle errors with the SDK
order: 100
---

# Error Handling

SDK methods handle failure by throwing exceptions inheriting the `KuzzleException` class, which is a standard Java exception extending the `Throwable` class.

You can find a detailed list of possible errors messages and statuses in the [documentation API](/core/1/api/essentials/errors).
Just replace _Error_ by _Exception_ to find the exception name. (e.g. `BadRequestError` becomes `BadRequestException`).

#### Example

<<< ./snippets/error-handling.java

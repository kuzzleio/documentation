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

All methods return an "error" struct, which holds a non-nil value if the call failed.
Error structs are all of type `KuzzleError`.

The `KuzzleError` type implements the standard `error` interface, and adds the following properties to it:

| Property   | Type    | Description                       |
| ---------- | ------- | --------------------------------- |
| `Status` | int | Status following [HTTP Standards](https://en.wikipedia.org/wiki/List_of_HTTP_status_codes) |
| `Stack` | string | Error stacktrace (Only in development mode) |

You can find a detailed list of possible errors messages and statuses in the [documentation API]({{ site_base_path }}api/1/errors).  

#### Example
[snippet=error-handling]

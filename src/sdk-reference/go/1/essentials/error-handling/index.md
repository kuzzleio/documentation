---
layout: sdk.html.hbs
algolia: true
title: Error Handling
description: How to handle errors with the SDK
order: 100
---

# Error Handling

Each method returns at least one structure corresponding to a possible error.  
When this structure is different from nil, it is because the method has failed.  

The returned structure is an `error` that can be casted to a `KuzzleError` and it contains 3 properties:

| Property   | Type    | Description                       |
| ---------- | ------- | --------------------------------- |
| `Message` | string | Message describing the error |
| `Status` | int | Status following [HTTP Standards](https://en.wikipedia.org/wiki/List_of_HTTP_status_codes) |
| `Stack` | string | Error stacktrace (Only in development mode) |

You can find a detailed list of possible errors messages and statuses in the [documentation API]({{ site_base_path }}api/1/errors).  

#### Example
[snippet=error-handling]

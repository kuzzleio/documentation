---
layout: sdk.html.hbs
algolia: true
title: Error Handling
description: How to handle errors with the SDK
---

# Error Handling

All SDK methods return a promise, that can be rejected with a `KuzzleError` value in case of failure.

`KuzzleError` objects inherit the standard `Error` object, and add the following properties to it:


| Property   | Type    | Description                       |
| ---------- | ------- | --------------------------------- |
| `status` | Number | Status following [HTTP Standards](https://en.wikipedia.org/wiki/List_of_HTTP_status_codes) |
| `stack` | String | Error stacktrace (Only in development mode) |

You can find a detailed list of possible errors messages and statuses in the [documentation API]({{ site_base_path }}api/1/errors).

#### Example with a promise chain
[snippet=error-handling]

#### Example with async/await
[snippet=error-handling-async]

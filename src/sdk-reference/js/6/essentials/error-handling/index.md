---
layout: sdk.html.hbs
algolia: true
title: Error Handling
description: How to handle errors with the SDK
order: 100
---

# Error Handling

Each of the SDK methods is likely to fail and return an error.  

When a method fails, the promise is rejected and the error must be handled:
  - with the `.catch()` method of the promise chain
  - within the `catch` block when used with async / await

Errors are objects containing the following properties:

| Property   | Type    | Description                       |
| ---------- | ------- | --------------------------------- |
| `message` | String | Message describing the error |
| `status` | Number | Status following [HTTP Standards](https://en.wikipedia.org/wiki/List_of_HTTP_status_codes) |
| `stack` | String | Error stacktrace (Only in development mode) |

You can find a detailed list of possible errors messages and statuses in the [documentation API]({{ site_base_path }}api/1/errors).

#### Exemple with a promise chain
[snippet=error-handling]

#### Exemple with async/await
[snippet=error-handling-async]

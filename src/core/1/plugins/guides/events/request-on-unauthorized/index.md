---
code: true
type: page
title: request:onUnauthorized
---

# request:onUnauthorized



| Arguments | Type                                                           | Description                |
| --------- | -------------------------------------------------------------- | -------------------------- |
| `request` | <pre><a href=/plugins/1/constructors/request>Request</a></pre> | The normalized API request |

Triggered whenever a request fails authorization checks, and is about to be rejected with a `401` error code.

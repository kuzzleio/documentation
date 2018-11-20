---
layout: full.html.hbs
algolia: true
title: request:onUnauthorized
---


# request:onUnauthorized

{{{since "1.0.0"}}}

| Arguments | Type | Description |
|-----------|------|-------------|
| `request` | <pre><a href={{ site_base_path }}plugins/1/constructors/request>Request</a></pre> | The normalized API request |

Triggered whenever a request fails authorization checks, and is about to be rejected with a `401` error code.

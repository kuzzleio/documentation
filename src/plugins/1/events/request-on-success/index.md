---
layout: full.html.hbs
algolia: true
title: request:onSuccess
algolia: true
---

# request:onSuccess

{{{since "1.0.0"}}}

| Arguments | Type | Description |
|-----------|------|-------------|
| `request` | <pre><a href={{ site_base_path }}plugins/1/constructors/request>Request</a></pre> | The normalized API request |

Triggered whenever a request execution succeeds.

This event occurs after [after events]({{ site_base_path }}plugins/1/events/api-events/#after-default).

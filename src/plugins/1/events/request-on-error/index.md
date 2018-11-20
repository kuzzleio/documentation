---
layout: full.html.hbs
algolia: true
title: request:onError
---


# request:onError

{{{since "1.0.0"}}}

| Arguments | Type | Description |
|-----------|------|-------------|
| `request` | <pre><a href={{ site_base_path }}plugins/1/constructors/request>Request</a></pre> | The normalized API request |

Triggered whenever a request execution fails.  

This event occurs after [error events]({{ site_base_path }}plugins/1/events/api-events/#error-default).


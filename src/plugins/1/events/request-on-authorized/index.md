---
layout: full.html.hbs
title: request:onAuthorized
---

# request:onAuthorized

| Arguments | Type | Description |
|-----------|------|-------------|
| `request` | <pre><a href={{ site_base_path }}plugins/1/constructors/request>Request</a></pre> | The normalized API request |

Triggered whenever a request passes authorization checks and is ready to be processed.  

This event occurs before [before events]({{ site_base_path }}plugins/1/events/api-events/#before-default).

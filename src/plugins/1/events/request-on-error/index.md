---
layout: full.html.hbs
algolia: true
title: request:onError
---

# request:onError

{{{since "1.0.0"}}}

Triggered whenever a request execution fails.  
This event occurs after [error events]({{ site_base_path }}plugins/1/events/api-events/#error-default).

---

## Payload

* a [Request]({{ site_base_path }}plugins/1/constructors/request) object

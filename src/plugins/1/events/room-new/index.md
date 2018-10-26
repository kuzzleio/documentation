---
layout: full.html.hbs
algolia: true
title: room:new
---

# room:new

{{{since "1.0.0"}}}

Triggered whenever a new [subscription]({{ site_base_path }}api/1/controller-realtime/subscribe) is created.

<div class="alert alert-info">Pipes cannot listen to that event, only hooks can.</div>

---

## Payload

* `room`: {object} room information
  * `index`: data index name
  * `collection`: data collection name
  * `roomId`: the new room unique identifier

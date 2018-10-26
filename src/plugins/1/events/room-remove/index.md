---
layout: full.html.hbs
algolia: true
title: room:remove
---

# room:remove

{{{since "1.0.0"}}}

Triggered whenever a real-time subscription is cancelled.

<div class="alert alert-info">Pipes cannot listen to that event, only hooks can.</div>

---

## Payload

* `roomId`: {string} room unique identifier

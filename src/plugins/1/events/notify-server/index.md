---
layout: full.html.hbs
algolia: true
title: notify:server
---

# notify:server

{{{since "1.0.0"}}}

Triggered whenever a real-time server notification is about to be sent.

A [pipe]({{ site_base_path }}plugins/1/essentials/pipes/) can block some (or all) notifications by rejecting the provided promise.

---

## Payload

* a [Notification]({{ site_base_path }}api/1/notifications/) object


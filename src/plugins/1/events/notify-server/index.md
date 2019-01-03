---
layout: full.html.hbs
title: notify:server
---

# notify:server

{{{since "1.0.0"}}}

| Arguments | Type | Description |
|-----------|------|-------------|
| `message` | <pre><a href={{ site_base_path }}api/1/essentials/real-time>Notification</a></pre> | The normalized real-time notification |

Triggered whenever a real-time server notification is about to be sent.

A [pipe]({{ site_base_path }}plugins/1/essentials/pipes/) can block some (or all) notifications by rejecting the provided promise.

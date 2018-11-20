---
layout: full.html.hbs
algolia: true
title: notify:document
---


# notify:document

{{{since "1.0.0"}}}

| Arguments | Type | Description |
|-----------|------|-------------|
| `message` | <pre><a href={{ site_base_path }}api/1/notifications>Notification</a></pre> | The normalized real-time notification |

Triggered whenever a real-time document notification is about to be sent.

A [pipe]({{ site_base_path }}plugins/1/essentials/pipes/) can block some (or all) notifications by rejecting the provided promise.

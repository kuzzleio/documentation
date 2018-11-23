---
layout: full.html.hbs
algolia: true
title: room:new
algolia: true
---

# room:new

{{{since "1.0.0"}}}

| Arguments | Type | Description |
|-----------|------|-------------|
| `room` | <pre>object</pre> | Joined room information |

Triggered whenever a new [subscription]({{ site_base_path }}api/1/controller-realtime/subscribe) is created.

<div class="alert alert-info">Pipes cannot listen to that event, only hooks can.</div>

---

## room

The provided `room` object has the following properties:

| Properties | Type | Description |
|-----------|------|-------------|
| `index` | <pre>string</pre> | Data index name |
| `collection` | <pre>string</pre> | Data collection name |
| `roomId` | <pre>string</pre> | The new room unique identifier |

---
code: true
type: page
title: room:new
---

# room:new



| Arguments | Type              | Description             |
| --------- | ----------------- | ----------------------- |
| `room`    | <pre>object</pre> | Joined room information |

Triggered whenever a new [subscription](/core/1/api/api-reference/controller-realtime/subscribe/) is created.

<div class="alert alert-info">Pipes cannot listen to that event, only hooks can.</div>

---

## room

The provided `room` object has the following properties:

| Properties   | Type              | Description                    |
| ------------ | ----------------- | ------------------------------ |
| `index`      | <pre>string</pre> | Index name                     |
| `collection` | <pre>string</pre> | Collection name                |
| `roomId`     | <pre>string</pre> | The new room unique identifier |

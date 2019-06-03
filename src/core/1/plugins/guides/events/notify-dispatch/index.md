---
code: true
type: page
title: notify:dispatch
---

# notify:dispatch



| Arguments | Type                                                                     | Description                           |
| --------- | ------------------------------------------------------------------------ | ------------------------------------- |
| `message` | <pre><a href=/core/1/api/essentials/notifications>Notification</a></pre> | The normalized real-time notification |

Triggered whenever a real-time notification is about to be sent.

A [pipe](/core/1/plugins/guides/pipes/) can block some (or all) notifications by rejecting the provided promise.

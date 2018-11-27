---
layout: full.html.hbs
algolia: true
title: notify
algolia: true
---

# notify

Asks the protocol to send data to a specific connection, on some of its [channels]({{ site_base_path }}protocols/1/essentials/getting-started/#channels-default).

---

## Arguments

`notify(channels, connectionId, payload)`

* `channels` | <pre>string[]</pre> | list of channels
* `connectionId` | <pre>string</pre> | connection unique identifier, previously registered by the protocol using [newConnection]({{ site_base_path }}protocols/1/entrypoint/newconnection)
* `payload` | <pre>object</pre> | data payload

---

## Return

The `notify` function is not expected to return a value.

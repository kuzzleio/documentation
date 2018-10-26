---
layout: full.html.hbs
algolia: true
title: joinChannel
---

# joinChannel

Informs the protocol that one of its connected users joined a [channel]({{ site_base_path }}protocols/1/essentials/getting-started/#channels-default).

---

## Arguments

`joinChannel(channel, connectionId)`

* `channel`: {string} joined channel identifier
* `connectionId`: {string} connection unique identifier, previously registered by the protocol using [newConnection]({{ site_base_path }}protocols/1/entrypoint/newconnection)

---

## Return

The `joinChannel` function is not expected to return a value.

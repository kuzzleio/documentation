---
layout: full.html.hbs
algolia: true
title: leaveChannel
---

# leaveChannel

Informs the protocol that one of its connected users left a [channel]({{ site_base_path }}protocols/1/essentials/getting-started/#channels-default).

---

## Arguments

`leaveChannel(channel, connectionId)`

* `channel`: {string} left channel identifier
* `connectionId`: {string} connection unique identifier, previously registered by the protocol using [newConnection]({{ site_base_path }}protocols/1/entrypoint/newconnection)

---

## Return

The `leaveChannel` function is not expected to return a value.

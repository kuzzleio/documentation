---
layout: full.html.hbs
algolia: true
title: disconnect
---

# disconnect

Asks a protocol to force-close a connection.

---

## Arguments

`disconnect(connectionId)`

* `connectionId`: {string} connection unique identifier, previously registered by the protocol using [newConnection]({{ site_base_path }}protocols/1/entrypoint/newconnection)

---

## Return

The `disconnect` function is not expected to return a value.

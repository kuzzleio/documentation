---
layout: full.html.hbs
algolia: true
title: notify
---

# notify

Asks protocols to send data to a specific user, on some of its [channels]({{ site_base_path }}protocols/1/essentials/getting-started/#channels-default).

---

## Arguments

`notify(channels, connectionId, payload)`

* `channels`: {string[]} list of channels
* `connectionId`: {string} connection unique identifier, previously registered by the protocol using [newConnection]({{ site_base_path }}protocols/1/entrypoint/newconnection)
* `payload`: {object} data payload

---

## Return

The `notify` function is not expected to return a value.

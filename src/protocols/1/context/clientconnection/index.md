---
layout: full.html.hbs
algolia: true
title: ClientConnection
---

# ClientConnection

{{{since "1.0.0"}}}

The `ClientConnection` class must be instantiated whenever a new client connection is created, and that instance must be provided to the [entryPoint.newConnection]({{ site_base_path }}protocols/1/entrypoint/newconnection) method.

---

## Arguments

`new context.ClientConnection(protocol, ips, headers)`

* `protocol`: {string} the protocol unique identifier
* `ips`: {string[]} list of forwarded ip addresses (or any client connection information) of the connection
* `headers`: {optional, object} extra connection information

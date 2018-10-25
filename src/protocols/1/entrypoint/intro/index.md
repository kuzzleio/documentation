---
layout: full.html.hbs
algolia: true
title: Properties
order: 0
---

# Properties

An instance of the `EntryPoint` class is provided as an argument to the protocol's required [init]({{ site_base_path }}protocols/1/essentials/interface/#init-default) function.

That instance contains the necessary methods to make Kuzzle aware of user connections, and to pass API requests to Kuzzle.

---

## Properties

* `config`: {object} Link to the `server` section of Kuzzle's [configuration]({{ site_base_path }}guide/1/essentials/configuration/) file. Custom configuration for the protocol can be found at the following path: `entryPoint.config.protocols.<protocol unique name>`.
* `httpServer`: {object} Kuzzle's [HTTP server](https://nodejs.org/dist/latest-v8.x/docs/api/http.html#http_class_http_server)
* `isShuttingDown`: {boolean} if `true`, then this Kuzzle node is shutting down, and it will kill itself once all ongoing requests are finished. The network protocol should not accept any new request when  this flag is active

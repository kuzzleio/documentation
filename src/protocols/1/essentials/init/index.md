---
layout: full.html.hbs
algolia: true
title: init
---

# init

Initializes the protocol. 

Called once, during Kuzzle startup.

---

## Arguments

`init (entryPoint, context)`

* `entryPoint`: {[EntryPoint]({{ site_base_path }}protocols/1/entrypoint)} provides an interface to protocol related methods
* `context`: {[context]({{ site_base_path }}protocols/1/context) generic interface to non-related Kuzzle functions

---

## Return

The `init` function can optionally return a promise. If it does, Kuzzle waits for the promise to be resolved before continuing its own initialization.

If a promise is returned, it must be resolved within the configured timeout (see `services.common.defaultInitTimeout` in Kuzzle's [configuration]({{ site_base_path }}guide/1/essentials/configuration/))

If a promise is returned and rejected, or if the `init` function throws an error, Kuzzle aborts its start sequence and shuts down.

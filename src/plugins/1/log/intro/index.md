---
layout: full.html.hbs
algolia: true
title: Introduction
order: -10
---

# Introduction

The plugin context is an object containing a set of constructors, accessors and various other helpers, allowing plugins to interact with Kuzzle.

Each plugin receives its own context instance, provided to the plugin's [init function]({{ site_base_path }}plugins/1/essentials/getting-started/#init-function-default).

This section documents the `context.log` object, exposing functions used to send messages to Kuzzle's logging system.

Log levels are assigned to each exposed log function, corresponding to the log priority.  
The lower the log level, the higher its priority.

Levels above the threshold configued in the `logs` section of Kuzzle's [configuration file]({{ site_base_path }}guide/1/essentials/configuration) are ignored.


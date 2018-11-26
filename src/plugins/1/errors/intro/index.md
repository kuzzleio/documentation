---
layout: full.html.hbs
algolia: true
title: Introduction
order: 0
algolia: true
---

# Introduction

Whenever a plugin returns, rejects or throws an error, Kuzzle intercepts it and, by default, rethrows it as a [PluginImplementationError]({{ site_base_path }}plugins/1/errors/pluginimplementationerror) error.

To prevent this, plugins have error constructors at their disposal. Plugins can also create their own errors by inheriting from the abstract [KuzzleError]({{ site_base_path }}plugins/1/errors/kuzzleerror) object.

This section details the error constructors made available in the plugin context, an object containing a set of constructors, accessors and various other helpers, allowing plugins to interact with Kuzzle.

Each plugin receives its own context instance, provided to the plugin's [init function]({{ site_base_path }}plugins/1/essentials/getting-started/#init-function-default).

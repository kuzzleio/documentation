---
layout: full.html.hbs
algolia: true
title: Introduction
order: 0
---

# Introduction

The plugin context is an object containing a set of constructors, accessors and various other helpers, allowing plugins to interact with Kuzzle.

Each plugin receives its own context instance, provided to the plugin's [init function]({{ site_base_path }}plugins/1/essentials/getting-started/#init-function-default).

This section details the accessors made available in the context.

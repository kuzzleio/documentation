---
layout: full.html.hbs
algolia: true
title: Getting Started
description: how to create a custom plugin
order: 0
---


# Getting Started

Kuzzle can be customized and extended using plugins.

This chapter explains how to install and configure a plugin. The other chapters in this section cover the four plugin interfaces exposed by Kuzzle:

* [Hooks](../hooks)
* [Pipes](../pipes)
* [Controllers](../controllers)
* [Authentication Strategies](../strategies)

A single plugin can implement as many of those interfaces as necessary.


## init function

Plugins must expose an `init` function. If it is missing, Kuzzle fails to load the plugin and aborts its start sequence.

The `init` method is called once during startup, and it is used to initialize the plugin.

### Arguments

```js
init (config, context)
```

<br/>

| Arguments | Type | Description |
|
## Configuration

When Kuzzle calls the plugin `init` method, it passes the plugin's custom configuration to it.

Custom configuration parameters are specified for each plugin in the `plugins` object of the Kuzzle [configuration file]({{ site_base_path }}guide/1/essentials/configuration). 

For example:

```json
{
  "plugins": {
    "foobar-plugin": {
      "option_1": "option_value",
      "option_2": "option_value"
    }
  }
}
```

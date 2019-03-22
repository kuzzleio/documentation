---
layout: full.html.hbs
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

---

## Plugin Boilerplate

The best way to start developing a plugin is to use one of our boilerplates.

These boilerplates contain a Kuzzle stack that reloads itself whenever a plugin change is detected, making them handy tools for plugin development.

2 boilerplates are available:
 - [kuzzle-core-plugin-boilerplate](https://github.com/kuzzleio/kuzzle-core-plugin-boilerplate): contains a simple example of all plugin functionnalities
 - [kuzzle-plugin-advanced-boilerplate](https://github.com/kuzzleio/kuzzle-plugin-advanced-boilerplate): production boilerplate with better separation of concerns

Clone one of these repositories to start developing a Kuzzle plugin:

```bash
git clone https://github.com/kuzzleio/kuzzle-core-plugin-boilerplate
cd kuzzle-core-plugin-boilerplate
docker-compose -f docker/docker-compose.yml up
// Kuzzle stack with the plugin is ready
// Edit the file lib/index.js, 
// the Kuzzle stack will automaticaly restart to include your modifications
```

## Prerequisites

### Location

Plugins are subdirectories that must be put at the following location: `<kuzzle_install_dir>/plugins/enabled`.

The recommended way to install a plugin is to put it in `plugins/available`, and then link it to the `plugins/enabled` directory.

### Node.js modules

Kuzzle loads plugins as [Node.js modules](https://nodejs.org/dist/latest-v8.x/docs/api/modules.html).

This means that a plugin directory must contain either:

* an `index.js` file 

and/or:

* a valid [package.json](https://docs.npmjs.com/files/package.json) file. If the plugin's entrypoint is not the root `index.js` file, then the [main](https://docs.npmjs.com/files/package.json#main) property must be filled

### manifest.json

Kuzzle needs a few information to make your plugin work properly. These information must be provided in a `manifest.json` file, in the plugin directory.

The following properties can be defined in this `manifest.json` file:

* `name` (**required**): plugin unique identifier. Names can only contain lowercase letters, numbers, hyphens and underscores. 
* `kuzzleVersion`: a non-empty string describing a [semver range](https://www.npmjs.com/package/semver#ranges), limiting the range of Kuzzle versions supported by this plugin. If not set, a warning is displayed on the console, and Kuzzle assumes that the plugin is only compatible with Kuzzle v1.x

{{{deprecated "1.5.0"}}} Kuzzle still allows plugins to be loaded without a <code>manifest.json</code> file, for backward compatibility reasons, falling back to the <a href=https://docs.npmjs.com/files/package.json#name>package.json</a> file to retrieve the plugin's name. This will change in next major releases of Kuzzle.

---

## init function

Plugins must expose an `init` function. If it is missing, Kuzzle fails to load the plugin and aborts its start sequence.

The `init` method is called once during startup, and it is used to initialize the plugin.

### Arguments

```js
init (config, context)
```

<br/>

| Arguments | Type | Description |
|-----------|------|-------------|
| `config` | <pre>object</pre> | Contains the custom plugin configuration (see the [configuration](#configuration-default) chapter) |
| `context` | <pre>object</pre> | The plugin context, exposing various accessors, constructors, and helpers. The other sections of this documentation detail the interfaces made available by this object |

### Return

The `init` function can optionally return a promise. If it does, Kuzzle waits for the promise to be resolved before continuing its own initialization.

If a promise is returned, it must be resolved within the configured timeout (see `plugins.common.initTimeout` in Kuzzle's [configuration]({{ site_base_path }}guide/1/essentials/configuration/))

If a promise is returned and rejected, or if the `init` function throws an error, Kuzzle aborts its start sequence and shuts down.

---

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

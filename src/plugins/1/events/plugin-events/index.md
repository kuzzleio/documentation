---
layout: full.html.hbs
algolia: true
title: "> Plugin Events"
order: 200
algolia: true
---

# Plugin Events

{{{since "1.0.0"}}}

Plugins can [add new controllers]({{ site_base_path }}plugins/1/essentials/controllers) to the Kuzzle API. 

These new controllers and actions behave exactly like [native API actions]({{ site_base_path }}plugins/1/events/api-events/).  
All calls to plugins API actions trigger two of these three events:

* before the action starts
* after it succeeds
* after it fails

---

## before

| Arguments | Type | Description |
|-----------|------|-------------|
| `request` | <pre><a href={{ site_base_path }}plugins/1/constructors/request>Request</a></pre> | The normalized API request |

A `before` event is triggered before a plugin API request starts.

### Naming Template

The `before` event name is built using the following template:

`<plugin name>/<controller>:before<Action>`

* `plugin name`: the plugin's name defined in the [manifest file]({{ site_base_path }}plugins/1/essentials/getting-started/#prerequisites-default)
* `controller`: API controller name
* `Action`: controller action, camel cased

#### Example

| Plugin name | API controller | Action   | After event name    |
|-------------|----------------|----------|---------------------|
| `plugin`    | `controller`   | `action` | `plugin/controller:beforeAction` |

---

## after

| Arguments | Type | Description |
|-----------|------|-------------|
| `request` | <pre><a href={{ site_base_path }}plugins/1/constructors/request>Request</a></pre> | The normalized API request |

An `after` event is triggered after a plugin API request succeeds.

### Naming Template

The `after` event name is built using the following template:

`<plugin name>/<controller>:after<Action>`

* `plugin name`: the plugin's name defined in the [manifest file]({{ site_base_path }}plugins/1/essentials/getting-started/#prerequisites-default)
* `controller`: API controller name
* `Action`: controller action, camel cased

#### Example

| Plugin name | API controller | Action   | After event name    |
|-------------|----------------|----------|---------------------|
| `plugin`    | `controller`   | `action` | `plugin/controller:afterAction` |

---

## error

| Arguments | Type | Description |
|-----------|------|-------------|
| `request` | <pre><a href={{ site_base_path }}plugins/1/constructors/request>Request</a></pre> | The normalized API request |

An `error` event is triggered after a plugin API request fails.

### Naming Template

The `error` event name is built using the following template:

`<plugin name>/<controller>:error<Action>`

* `plugin name`: the plugin's name defined in the [manifest file]({{ site_base_path }}plugins/1/essentials/getting-started/#prerequisites-default)
* `controller`: API controller name
* `Action`: controller action, camel cased

#### Example

| Plugin name | API controller | Action   | After event name    |
|-------------|----------------|----------|---------------------|
| `plugin`    | `controller`   | `action` | `plugin/controller:errorAction` |

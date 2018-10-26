---
layout: full.html.hbs
algolia: true
title: "> Plugin Events"
order: 200
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

A `before` event is triggered before the plugin API action starts.

### Template

The `before` event is built using the following template:

`<plugin name>/<controller>:before<Action>`

* `plugin name`: the plugin's name defined in the [manifest file]({{ site_base_path }}plugins/1/essentials/getting-started/#prerequisites-default)
* `controller`: API controller name
* `Action`: controller action, camel cased

### Payload

* a [Request]({{ site_base_path }}plugins/1/constructors/request) object

### Example

| Plugin name | API controller | Action   | After event name    |
|-------------|----------------|----------|---------------------|
| `plugin`    | `controller`   | `action` | `plugin/controller:beforeAction` |

---

## after

An `after` event is triggered after the plugin API action succeeds.

### Template

The `after` event is built using the following template:

`<plugin name>/<controller>:after<Action>`

* `plugin name`: the plugin's name defined in the [manifest file]({{ site_base_path }}plugins/1/essentials/getting-started/#prerequisites-default)
* `controller`: API controller name
* `Action`: controller action, camel cased

### Payload

* a [Request]({{ site_base_path }}plugins/1/constructors/request) object

### Example

| Plugin name | API controller | Action   | After event name    |
|-------------|----------------|----------|---------------------|
| `plugin`    | `controller`   | `action` | `plugin/controller:afterAction` |

---

## error

An `error` event is triggered after the plugin API action fails.

### Template

The `error` event is built using the following template:

`<plugin name>/<controller>:error<Action>`

* `plugin name`: the plugin's name defined in the [manifest file]({{ site_base_path }}plugins/1/essentials/getting-started/#prerequisites-default)
* `controller`: API controller name
* `Action`: controller action, camel cased

### Payload

* a [Request]({{ site_base_path }}plugins/1/constructors/request) object

### Example

| Plugin name | API controller | Action   | After event name    |
|-------------|----------------|----------|---------------------|
| `plugin`    | `controller`   | `action` | `plugin/controller:errorAction` |

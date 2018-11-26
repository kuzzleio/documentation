---
layout: full.html.hbs
algolia: true
title: "> API Events"
order: 100
algolia: true
---

# API Events

{{{since "1.0.0"}}}

All API actions, without exception, trigger two of these three events: 

* before the action starts
* after it succeeds
* after it fails

---

## before

| Arguments | Type | Description |
|-----------|------|-------------|
| `request` | <pre><a href={{ site_base_path }}plugins/1/constructors/request>Request</a></pre> | The normalized API request |

A `before` event is triggered before an API request starts.

### Naming Template

The `before` event name is built using the following template:

`<controller>:before<Action>`

* `controller`: API controller name
* `Action`: controller action, camel cased

#### Example

| API action | After event name |
|------------|------------------|
| [auth:login]({{ site_base_path }}api/1/controller-auth/login) | `auth:beforeLogin` |
| [document:createOrReplace]({{ site_base_path }}api/1/controller-document/create-or-replace) | `document:beforeCreateOrReplace` |

---

## after

| Arguments | Type | Description |
|-----------|------|-------------|
| `request` | <pre><a href={{ site_base_path }}plugins/1/constructors/request>Request</a></pre> | The normalized API request |

An `after` event is triggered after an API request succeeds.

### Naming Template

The `after` event name is built using the following template:

`<controller>:after<Action>`

* `controller`: API controller name
* `Action`: controller action, camel cased

#### Example

| API action | After event name |
|------------|------------------|
| [auth:login]({{ site_base_path }}api/1/controller-auth/login) | `auth:afterLogin` |
| [document:createOrReplace]({{ site_base_path }}api/1/controller-document/create-or-replace) | `document:afterCreateOrReplace` |

---

## error

| Arguments | Type | Description |
|-----------|------|-------------|
| `request` | <pre><a href={{ site_base_path }}plugins/1/constructors/request>Request</a></pre> | The normalized API request |

An `error` event is triggered after an API request fails.

### Naming Template

The `error` event name is built using the following template:

`<controller>:error<Action>`

* `controller`: API controller name
* `Action`: controller action, camel cased

#### Example

| API action | After event name |
|------------|------------------|
| [auth:login]({{ site_base_path }}api/1/controller-auth/login) | `auth:errorLogin` |
| [document:createOrReplace]({{ site_base_path }}api/1/controller-document/create-or-replace) | `document:errorCreateOrReplace` |

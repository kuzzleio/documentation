---
layout: full.html.hbs
algolia: true
title: API Events
order: 100
---

# API Events

{{{since "1.0.0"}}}

All API actions, without exception, trigger two of these three events: 

* before the action starts
* after it succeeds
* after it fails

---

## before

A `before` event is triggered before the API action starts.

### Template

The `before` event is built using the following template:

`<controller>:before<Action>`

* `controller`: API controller name
* `Action`: controller action, camel cased

### Payload

* a [Request]({{ site_base_path }}plugins/1/constructors/request) object

### Example

| API action | After event name |
|------------|------------------|
| [auth:login]({{ site_base_path }}api/1/controller-auth/login) | `auth:beforeLogin` |
| [document:createOrReplace]({{ site_base_path }}api/1/controller-document/create-or-replace) | `document:beforeCreateOrReplace` |

---

## after

An `after` event is triggered after the API action succeeds.

### Template

The `after` event is built using the following template:

`<controller>:after<Action>`

* `controller`: API controller name
* `Action`: controller action, camel cased

### Payload

* a [Request]({{ site_base_path }}plugins/1/constructors/request) object

### Example

| API action | After event name |
|------------|------------------|
| [auth:login]({{ site_base_path }}api/1/controller-auth/login) | `auth:afterLogin` |
| [document:createOrReplace]({{ site_base_path }}api/1/controller-document/create-or-replace) | `document:afterCreateOrReplace` |

---

## error

An `error` event is triggered after the API action fails.

### Template

The `error` event is built using the following template:

`<controller>:error<Action>`

* `controller`: API controller name
* `Action`: controller action, camel cased

### Payload

* a [Request]({{ site_base_path }}plugins/1/constructors/request) object

### Example

| API action | After event name |
|------------|------------------|
| [auth:login]({{ site_base_path }}api/1/controller-auth/login) | `auth:errorLogin` |
| [document:createOrReplace]({{ site_base_path }}api/1/controller-document/create-or-replace) | `document:errorCreateOrReplace` |

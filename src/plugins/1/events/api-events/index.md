---
layout: full.html.hbs
algolia: true
title: "> API Events"
order: 100
---


# API Events

{{{since "1.0.0"}}}

All API actions, without exception, trigger two of these three events: 

* before the action starts
* after it succeeds
* after it fails


## after

| Arguments | Type | Description |
|
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

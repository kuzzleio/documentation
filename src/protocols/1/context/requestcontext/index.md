---
layout: full.html.hbs
algolia: true
title: RequestContext
---

# RequestContext

Connection context.

This is the class used to build the `context` property of any [Request]({{ site_base_path }}protocols/1/context/request) object.

Technical information: [github repository](https://github.com/kuzzleio/kuzzle-common-objects/blob/master/README.md#modelsrequestcontext)

---

## Constructor

`new RequestContext([options])`

* `options`: {optional, object} optional arguments
  * `connection`: {[ClientConnection]({{ site_base_path }}protocols/1/context/clientconnection)} connection information
  * `token`: {string} authorization token
  * `user`: {object} Kuzzle internal user information

---

## Properties

* `connection`: {[ClientConnection]({{ site_base_path }}protocols/1/context/clientconnection)} connection information
* `token`: {string} authorization token
* `user`: {object} Kuzzle internal user information

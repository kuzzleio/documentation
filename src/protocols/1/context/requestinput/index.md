---
layout: full.html.hbs
algolia: true
title: RequestInput
---

# RequestInput

Request input, normalizing a [Kuzzle API call]({{ site_base_path }}api/1/query-syntax/#other-protocols-default) in JSON format.

This is the class used to build the `input` property of any [Request]({{ site_base_path }}protocols/1/context/request) object.

Technical information: [github repository](https://github.com/kuzzleio/kuzzle-common-objects/blob/master/README.md#modelsrequestinput)

---

## Constructor

`new RequestInput(data)`

* `data`: {object} API request, in JSON format. Properties not in the list below are stored in the `args` object:
  * `_id`: {string} resource unique identifier
  * `action`: {string} invoked API controller's action
  * `body`: {object} request specific data (document content, search queries, ...)
  * `collection`: {string} data collection
  * `controller`: {string} invoked API controller
  * `index`: {string} data index
  * `jwt`: {string}  authentication token
  * `volatile`: {object} request [volatile data]({{ site_base_path }}api/1/volatile-data/)

---

## Properties

* `action`: {string} invoked API controller's action
* `args`: {object} request specific arguments
* `body`: {object} request specific data
* `controller`: {string} invoked API controller
* `jwt`: { string} authentication token
* `resource`: {object} stored resource target
  * `_id`: {string} resource unique identifier
  * `collection`: {string} data collection
  * `index`: {string} data index
* `volatile`: {object} request [volatile data]({{ site_base_path }}api/1/volatile-data/)

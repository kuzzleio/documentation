---
layout: full.html.hbs
algolia: true
title: KuzzleError
order: 10
---

# KuzzleError

{{{since "1.0.0"}}}

Inherits from the standard Javascript `Error` object: abstract class inherited by all Kuzzle error objects.

This class should only be used to create new Kuzzle error objects.

## Properties

* `message`: {string} error message
* `stack`: {string[]} error stack trace (not available in production mode)
* `status`: {integer} error status code, following the standard [HTTP status code](https://en.wikipedia.org/wiki/List_of_HTTP_status_codes)

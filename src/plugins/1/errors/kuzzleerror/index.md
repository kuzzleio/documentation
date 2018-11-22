---
layout: full.html.hbs
algolia: true
title: KuzzleError
order: 10
algolia: true
---

# KuzzleError

{{{since "1.0.0"}}}

Inherits from the standard Javascript `Error` object: abstract class inherited by all Kuzzle error objects.

This class should only be used to create new Kuzzle error objects.

## Properties

| Property | Type | Description |
|----------|------|-------------|
| `message` | <pre>string</pre> | Error message |
| `stack` | <pre>string[]</pre> | Error stack trace (not available in production mode) |
| `status` | <pre>integer</pre> | Error status code, following the standard [HTTP status code]( https://en.wikipedia.org/wiki/List_of_HTTP_status_codes) |

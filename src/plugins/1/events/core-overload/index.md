---
layout: full.html.hbs
title: core:overload
---

# core:overload

{{{since "1.0.0"}}}

| Arguments | Type | Description |
|-----------|------|-------------|
| `fill` | <pre>number</pre> | Request buffer fill percentage |

Triggered when the requests buffer fills up more quickly than requests can be processed.

The requests buffer is configurable through the `limits` parameters in the [Kuzzle configuration]({{ site_base_path }}guide/essentials/configuration/).

Requests submitted while the request buffer is completely filled (i.e. the payload is equal to `100`) are rejected with a [ServiceUnavailableError]({{ site_base_path }}api/1/errors/#common-errors-default) (code `503`)

<div class="alert alert-info">Pipes cannot listen to that event, only hooks can.</div>

---
layout: full.html.hbs
algolia: true
title: core:auth:strategyAdded
algolia: true
---

# core:auth:strategyAdded

{{{since "1.2.0"}}}

| Arguments | Type | Description |
|-----------|------|-------------|
| `strategy` | <pre>object</pre> | Authentication strategy information |

Triggered whenever a plugin [dynamically registers]({{ site_base_path }}plugins/1/accessors/strategies/) an authentication strategy.

<div class="alert alert-info">Pipes cannot listen to that event, only hooks can.</div>

---

## strategy

The provided `strategy` object has the following properties:

| Properties | Type | Description |
|-----------|------|-------------|
| `pluginName` | <pre>string</pre> | The plugin's name defined in the [manifest file]({{ site_base_path }}plugins/1/essentials/getting-started/#prerequisites-default) |
| `name` | <pre>string</pre> | Authentication strategy name |
| `strategy` | <pre>object</pre> | Authentication [strategy properties]({{ site_base_path }}plugins/1/essentials/strategies/#managing-credentials-default) |

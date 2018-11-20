---
layout: full.html.hbs
algolia: true
title: core:auth:strategyAdded
---


# core:auth:strategyAdded

{{{since "1.2.0"}}}

| Arguments | Type | Description |
|
## strategy

The provided `strategy` object has the following properties:

| Properties | Type | Description |
|-----------|------|-------------|
| `pluginName` | <pre>string</pre> | The plugin's name defined in the [manifest file]({{ site_base_path }}plugins/1/essentials/getting-started/#prerequisites-default) |
| `name` | <pre>string</pre> | Authentication strategy name |
| `strategy` | <pre>object</pre> | Authentication [strategy properties]({{ site_base_path }}plugins/1/essentials/strategies/#managing-credentials-default) |

---
layout: full.html.hbs
algolia: true
title: core:auth:strategyAdded
---

# core:auth:strategyAdded

{{{since "1.2.0"}}}

Triggered whenever a plugin [dynamically registers]({{ site_base_path }}plugins/1/accessors/strategies/) an authentication strategy.

<div class="alert alert-info">Pipes cannot listen to that event, only hooks can.</div>

---

## Payload

* `strategy`: {object} information on the registered strategy.
  * `pluginName`: {string} the plugin's name defined in the [manifest file]({{ site_base_path }}plugins/1/essentials/getting-started/#prerequisites-default)
  * `name`: {string} authentication strategy name
  * `strategy`: authentication [strategy properties]({{ site_base_path }}plugins/1/essentials/strategies/#managing-credentials-default) 

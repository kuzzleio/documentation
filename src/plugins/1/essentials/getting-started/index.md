---
layout: full.html.hbs
title: Getting started
description: how to create a custom plugin
order: 1
---

# Plugin Boilerplate

The best way to start developing a plugin is to use a boilerplate.

We provide a boilerplate that contain a Kuzzle stack that reloads itself whenever a plugin change is detected, making it a handy tool for plugin development.

- [kuzzle-core-plugin-boilerplate](https://github.com/kuzzleio/kuzzle-core-plugin-boilerplate)

Clone this repository to start developing a Kuzzle plugin:

```bash
git clone https://github.com/kuzzleio/kuzzle-core-plugin-boilerplate
cd kuzzle-core-plugin-boilerplate
docker-compose -f docker/docker-compose.yml up
// Kuzzle stack with the plugin is ready
// Edit the file lib/index.js,
// the Kuzzle stack will automaticaly restart to include your modifications
```

The provided `docker-compose` launches a Kuzzle stack with the `pm2` module, you can:

- Enable hot reload by watching the plugin's files.
- Configure it using the file next to the `docker-compose` inside the docker folder.

The main Plugin class is defined in the `index.js`. You can start edit it adding:

- [Hooks]({{ site_base_path }}plugins/1/essentials/hooks/)
- [Pipes]({{ site_base_path }}plugins/1/essentials/pipes/)
- [Controllers]({{ site_base_path }}plugins/1/essentials/controllers/)
- [Authentication Strategies]({{ site_base_path }}plugins/1/strategies/overview/)

Since the `init` function is the very first to be called, it's loading the `configuration` and the `context`.
You can now write your own functions and your own routes as described inside the `index.js`. You can also write unit tests if you're a see `steps.js`.

<div class="alert alert-info">
You can find more information about the <code>init</code> function <a href="{{ site_base_path }}plugins/1/plugin-layout/init-function/"> here</a>.
</div>
<div class="alert alert-success">
You have now everything you need to start writing your own Kuzzle plugin.
</div>

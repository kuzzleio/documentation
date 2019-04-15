---
layout: full.html.hbs
title: Plugin Boilerplate
description: how to create a custom plugin
order: 0
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

The provided `docker-compose` launches a Kuzzle stack with the `pm2` module :
<br>It allows hot reload by watching the plugin's files.
<br>You can find its configuration file next to the `docker-compose` inside the docker folder.

The main Plugin class is defined in the `index.js`.
<br>You can start edit `hooks`, `pipes`, `controllers` and `strategies`.

Since the `init` function is the very first to be called, it's loading the `configuration` and the `context`.
<br>More information [here](https://docs.kuzzle.io/plugins/1/plugin-layout/init-function/).

You can now write your own functions and your own routes as described inside the `index.js`.

Also, you can write unit tests : see `steps.js`.

You have now everything you need to start writing your own Kuzzle plugin.

---

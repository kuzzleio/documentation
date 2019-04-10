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

---
layout: full.html.hbs
algolia: true
title: Kuzzle
description: Getting started with Kuzzle
---

# Getting Started with Kuzzle

In this tutorial you will learn how to install and run **Kuzzle**.

## Running Kuzzle

This section explains how to quickly get Kuzzle up and running using our installation script.

Open a terminal and run the following command:

```bash
bash -c "$(curl https://get.kuzzle.io/)"
```

This command downloads and executes the installation script. The script checks the system for a set of prerequisites and installs any necessary tools, like Docker or Docker Compose. When the installation is complete it will automatically run Kuzzle.

<div class="alert alert-info">
There are also more <a href="{{ site_base_path }}guide/1/essentials/installing-kuzzle/">alternative ways</a> to install Kuzzle.
</div>

This command downloads, installs and runs Kuzzle.

Use the `--no-run` option to prevent the script from running Kuzzle.

Once the installation process is complete, you will see the following message:

```bash
# Kuzzle is now running
```

Your Kuzzle is now running! To test it, you can explore the main HTTP API by clicking this <a href="http://localhost:7512?pretty">link</a> or by using cURL on the command line:

```bash
curl "http://localhost:7512/?pretty"
```

If everything is working you should see a JSON document that contains a list of API endpoints.

<div class="alert alert-success">
Congratulations! You have completed the Kuzzle installation, it will now accept requests on <code>localhost:7512</code>:
<ul>
  <li>via <strong>HTTP</strong></li>
  <li>via <strong>Websocket</strong></li>
</ul>
</div>

<div class="alert alert-info">
Having trouble?
<ul>
  <li>Get in touch with us on <a href="https://gitter.im/kuzzleio/kuzzle">Gitter!</a> We're happy to help.</li>
  <li>Try one of <a href="{{ site_base_path }}guide/1/essentials/installing-kuzzle/">these</a> alternative installation methods.</li>
</ul>
</div>

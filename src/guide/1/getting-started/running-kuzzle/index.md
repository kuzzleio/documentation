---
layout: full.html.hbs
title: Running Kuzzle
description: Running Kuzzle
order: 100
---

# Running Kuzzle

This section learns you how to quickly get Kuzzle up and running using our installation script.

Open a terminal and run the following command:

```bash
bash -c "$(curl https://get.kuzzle.io/)"
```

This command downloads and executes the installation script. The script checks the system for a set of prerequisites and installs missing ones, such as [Docker](https://www.docker.com/). When the installation is complete, it will automatically run Kuzzle.

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

If everything is working you should see a JSON document containings a list of API endpoints.

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

#### Helper scripts for systemd

If you want to run Kuzzle automatically at startup there are a few scripts that help you do this with systemd.

If you want to run Kuzzle automatically at startup there are a few scripts in `$PWD/kuzzle/script/` that help you do this with systemd:

* Run the `add-kuzzle-boot-systemd.sh` as root to add a service inside /etc/systemd/system that will start Kuzzle on boot.
* Run the `remove-kuzzle-boot-systemd.sh` as root to remove the service so that Kuzzle won't start on boot.

#### What now?

Now that Kuzzle is up and running, you can start playing around with it:

* install and learn a <a href="{{ site_base_path }}sdk-reference/">Kuzzle SDK</a> to power-up one of your projects
* install <a href="{{ site_base_path }}guide/1/essentials/installing-console">Kuzzle Admin Console</a>, a handy way to manage data and security in your Kuzzle installation
* explore the <a href="{{ site_base_path }}api/1">Kuzzle API</a> documentation
* install Kuzzle <a href="{{ site_base_path }}guide/1/essentials/installing-kuzzle/#manual-installation">without Docker</a>

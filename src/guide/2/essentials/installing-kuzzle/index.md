---
layout: full.html.hbs
algolia: true
title: Install Kuzzle
order: 1
---


# Installing Kuzzle

In this section we'll describe different ways of installing Kuzzle.


## Docker

Before launching Kuzzle using Docker containers, ensure that your system meets the following requirements:

- **64-bit environment**
- **Docker v1.10+**, see [instructions here](https://docs.docker.com/engine/installation/)
- **Docker Compose v1.8+**, see [instructions here](https://docs.docker.com/compose/install/)

To install docker, you need to download the docker-compose file:


Before starting the docker stack, you need to increase the maximum amount of virtual memory in order to run Elasticsearch, which is part of our stack (see why <a href="https://www.elastic.co/guide/2/en/elasticsearch/reference/5.x/vm-max-map-count.html">here</a>):

```bash
sudo sysctl -w vm.max_map_count=262144
```

To make this configuration permanent, you need to update your `/etc/sysctl.conf` file:

```bash
echo "vm.max_map_count=262144" >> /etc/sysctl.conf
```

Now, we can start the docker stack:

```bash
mkdir kuzzle-docker
cd kuzzle-docker
wget http://kuzzle.io/docker-compose.yml
docker-compose up
```

Your terminal should now be logging startup messages from the Kuzzle stack. After a few seconds, you should see the following message:

```bash
# kuzzle_1         | [✔] Kuzzle server ready
```

Your Kuzzle is now up and running. For a quick test, you can explore the main HTTP API endpoint by clicking this link <a href="http://localhost:7512">http://localhost:7512</a> or by using cURL on the command line:

```bash
curl "http://localhost:7512?pretty"
```


### Manual Installation

In this section we will perform a manual installation of Kuzzle on a Linux distribution. We choose Linux because all Kuzzle components work natively on it.

<div class="alert alert-info">
By default, Kuzzle expects all the components to be running on localhost but you can <a href="{{ site_base_path }}guide/2/essentials/configuration">change</a> this behavior.
</div>

We will run Kuzzle using [pm2](http://pm2.keymetrics.io/), a process management tool used to monitor Node.js applications.

### Supported operating systems

The following operating systems are actively supported (64-bit versions only):

* Ubuntu: 14.04+
* Debian: 7+

### Prerequisites

* [Elasticsearch](https://www.elastic.co/products/elasticsearch) version 6
* [Redis](http://redis.io/) version 3.x
* [Node.js](https://nodejs.org/en/download/package-manager/) version 8 or higher
* [NPM](https://www.npmjs.com/) version 3 or higher.
* [Python](https://www.python.org/) version 2.7 preferred.
* [GDB](https://www.gnu.org/software/gdb/) version 7.7 or higher.
* a C++11 compatible compiler.

<div class="alert alert-info">
 The last three prerequisites can be fulfilled on Debian-based systems by installing packages : `build-essential`, `gdb` and `python`.
</div>


## Where do we go from here?

Once your Kuzzle instance is up and running, dive even deeper to learn how to leverage its full capabilities:

- take a look at the [SDK Reference]({{site_base_path}}sdk-reference)
- learn how to use [Koncorde]({{ site_base_path }}kuzzle-dsl/2/essential/koncorde) to create incredibly fine-grained and blazing-fast subscriptions
- follow our guide to learn how to [implement basic authentication]({{site_base_path}}guide/2/essentials/user-authentication/#local-strategy).
- follow our guide to learn how to [implement manage users and setup fine-grained access control]({{site_base_path}}guide/2/essentials/security).

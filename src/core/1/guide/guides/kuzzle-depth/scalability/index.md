---
code: false
type: page
title: Scalability
---

# Scalability

Kuzzle can scale horizontally, provided our [official Cluster Plugin](https://github.com/kuzzleio/kuzzle-plugin-cluster) is installed.

This guide covers how clustering capabilities can be added to Kuzzle.

---

## Quick start

This chapter shows how to quickly create a Kuzzle cluster stack for development purposes. If you already have an existing Kuzzle server running, you may want to read the manual install chapter instead.

<div class="alert alert-info">
This development stack is for demonstration and test purposes only and should not be used as-is on production.<br/>
Notably, this stack only starts Kuzzle in cluster mode: Elasticsearch and Redis are not clustered.
</div>

Install and run: 

```bash
git clone git@github.com:kuzzleio/kuzzle-plugin-cluster
cd kuzzle-plugin-cluster
docker-compose -p cluster up --scale kuzzle=3
```

You should now have a Kuzzle cluster stack running with 3 Kuzzle nodes.

### ENOSPC error

On some Linux environments, you may get `ENOSPC` errors from the filesystem watcher, because of limits set too low.

If that happens, simply raise the limits on the number of files that can be watched:

`sudo sysctl -w fs.inotify.max_user_watches=524288`

That configuration change will last until the next reboot. 

To make it permanent, add the following line to your `/etc/sysctl.conf` file:

```
fs.inotify.max_user_watches=524288
```

---

## Manual install on an existing Kuzzle installation

To add cluster capabilities to an existing Kuzzle installation, the cluster plugin must be installed by following the [Plugin Install Guide]({{ site_base_path }}/guide/1/essentials/plugins/#installing-a-plugin).

<div class="alert alert-info">
If you are running Kuzzle in a Docker container, you will need to access the running container's shell and then the Kuzzle installation folder inside the container.
</div>

To install the cluster plugin, follow these steps:

```bash
cd <kuzzle directory>/plugins/available

git clone git@github.com:kuzzleio/kuzzle-plugin-cluster 

cd kuzzle-plugin-cluster
npm install # add --unsafe-perm if installing from inside a docker container

# Enable the installed plugin. Delete this link to disable it
cd ../../enabled
ln -s ../available/kuzzle-plugin-cluster
```

### Cluster plugin configuration

* The cluster plugin requires a privileged context from Kuzzle. This context is granted by Kuzzle via the global configuration.
* The cluster plugin registers a few [pipes]({{ site_base_path }}plugins/1/pipes), and some of them may exceed the default pipe timeouts. 

Add the following to your kuzzlerc configuration file (see our [Kuzzle configuration guide]({{ site_base_path }}guide/1/essentials/configuration/)):

```js
"plugins": {
  "common": {
    "pipeWarnTime": 5000,
    "pipeTimeout": 10000
  },
  "cluster": {
    "privileged": true
  }
}
```

Once the plugin installed and configured, you can start as many Kuzzle instances as you need, and they will automatically synchronize and work together.

---

## Extended API

The cluster plugin adds an [API controller]({{ site_base_path }}plugins/1/controllers) named `cluster`, with the following actions defined:

### health

The `cluster:health` API action returns the cluster health status.

#### HTTP

```
GET http://<host>:<port>/_plugin/cluster/health
```

#### Other Protocols

```js
{
  "controller": "cluster/cluster",
  "action": "health"
}
```

#### Result

```js
{
  "status": 200,
  "error": null,
  "controller": "cluster/cluster",
  "action": "health",
  "result": "ok"
}
````

### reset

The `cluster:reset` API action resets the cluster state and forces a resync.

#### HTTP

```
POST http://<host>:<port>/_plugin/cluster/reset
```

#### Other Protocols

```js
{
  "controller": "cluster/cluster",
  "action": "reset"
}
```

#### Result

```js
{
  "status": 200,
  "error": null,
  "controller": "cluster/cluster",
  "action": "reset",
  "result": "ok"
}
````

### status

The `cluster:status` API action returns the current cluster status.

#### HTTP

```
GET http://<host>:<port>/_plugin/cluster/status
```

#### Other Protocols

```js
{
  "controller": "cluster/cluster",
  "action": "status"
}
```

#### Result

```js
{
  "status": 200,
  "error": null,
  "controller": "cluster/cluster",
  "action": "status",
  "result": {
    "count": 3,
    "current": {
      "pub": "tcp://<kuzzle node IP>:7511",
      "router": "tcp://<kuzzle node IP>:7510",
      "ready": true
    },
    "pool": [
      {
        "pub": "tcp://<kuzzle node IP>:7511",
        "router": "tcp://<kuzzle node IP>:7510",
        "ready": true
      },
      {
        "pub": "tcp://<kuzzle node IP>:7511",
        "router": "tcp://<kuzzle node IP>:7510",
        "ready": true
      }
    ]
  }
}
```

---

## How a Kuzzle cluster works

### Auto-discovery and Synchronization

Kuzzle nodes are synchronized by maintaining their state in the Redis server instance.  
What this means is that, as long as Kuzzle nodes connect to the same Redis instance, they see each others and they work together.

Check our [Kuzzle configuration guide]({{ site_base_path }}guide/1/essentials/configuration/) to know how to make Kuzzle connect to specific Redis instances.

### Load Balancing

A load balancer in front of a Kuzzle cluster is hugely advised, to dispatch user connections to different Kuzzle nodes.  
Once assigned to a Kuzzle node, a client stays attached to it until their connection drop; when needed, a Kuzzle node automatically dispatches valuable information to other nodes.

Any load balancer will do. For instance, our development stack uses nginx for the sake of example.

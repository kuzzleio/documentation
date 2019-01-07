---
layout: full.html.hbs
title: Introduction
order: 0
description: IoT Code Examples
---

## Code Example: Real-Time Pub/Sub


Kuzzle comes equipped with a multi-protocol API that can be used to communicate with IoT devices. 

In this code example we will show you how to connect to Kuzzle using MQTT, a widely adopted communication protocol in IoT ecosystems.


## Install Kuzzle and MQTT Protocol Plugin

In order to enable the MQTT protocol in Kuzzle, we need to install the Kuzzle MQTT Protocol Plugin. To do this we need a slightly modified version of the Kuzzle installation. Specifically, we will need to open port `1883` used for MQTT and we will want to add a persistent volume to the Kuzzle container where we will install the protocol plugin so that it is not erased if we restart the container.

There are a few ways to do this, in our example we will create a modified version of Docker Compose file for the Kuzzle stack.

First decide where you want to install the plugin on your local machine. We will install it in our home folder under `/home/kuzzle/protocols`.

Create an `available` folder that will be used to store any protocol we download on our host: 

```bash
mkdir /home/kuzzle/protocols/available
```

In the `available` folder clone the MQTT Protocol Plugin:

```bash
cd /home/kuzzle/protocols/available
git clone https://github.com/kuzzleio/protocol-mqtt.git
```

Now create the `enabled` folder, that will be used to store any protocol we wish to enable in our Kuzzle instance. We will use a symbolic link to enable protocols as per these [recommendations]({{ site_base_path }}plugins/1/essentials/getting-started/#prerequisites-default).

```bash
mkdir /home/kuzzle/protocols/enabled
```

We will need to configure Kuzzle to use the `/home/kuzzle/protocols` folder from our host machine to load protocol plugins. To do this, we will mount a volume from our host machine into the Kuzzle Docker container.


Create the `docker-compose.yml` file with the following content:

```
version: '2'

services:
  kuzzle:
    image: kuzzleio/kuzzle
    ports:
      - "7512:7512"
      - "1883:1883"
    cap_add:
      - SYS_PTRACE
    depends_on:
      - redis
      - elasticsearch
    environment:
      - kuzzle_services__db__client__host=http://elasticsearch:9200
      - kuzzle_services__internalCache__node__host=redis
      - kuzzle_services__memoryStorage__node__host=redis
      - NODE_ENV=production
    volumes:
      - /home/kuzzle/protocols:/var/app/protocols

  redis:
    image: redis:3.2

  elasticsearch:
    image: docker.elastic.co/elasticsearch/elasticsearch:5.4.1
    environment:
      - cluster.name=kuzzle
      # disable xpack
      - xpack.security.enabled=false
      - xpack.monitoring.enabled=false
      - xpack.graph.enabled=false
      - xpack.watcher.enabled=false
    ports:
      - "9200:9200"
      - "9300:9300"
```

Note that we have opened port `1883` and mapped the host volume `/home/kuzzle/protocols` to the container volume `/var/app/protocols`.

Now run the stack using the following command (from the same folder as your docker-compose up.yml):

```bash
    docker-compose up -d
```

Now that the Kuzzle stack is running. We want to create a symbolic link to the available protocols folder inside the Kuzzle container:

```bash
    docker-compose exec kuzzle ln -s /var/app/protocols/available/protocol-mqtt  /var/app/protocols/enabled/protocol-mqtt
```

Now install the protocol plugin dependencies inside the docker container:

```bash
    docker-compose exec kuzzle npm install --prefix /var/app/protocols/enabled/protocol-mqtt
```

This might take a few minutes. When the dependencies finished installing, restart the Kuzzle container:

```bash
    docker-compose restart kuzzle 
```

Now your MQTT protocol should be up and running!


## Create Your App

Now that we have our Kuzzle configured, we can start programming our App. Here is an outline of what the App will do:
1. *Connect to Kuzzle*
3. *Subscribe to the MQTT Response Topic* to receive responses from the Kuzzle 
3. *Publish a request on the MQTT Request Topic* to send API requests to Kuzzle

Before we get started on the App, there are a few basics you need to know:

* Firstly, a subscription is done at the collection level. This means that Kuzzle will only monitor changes to documents in the specified collection.

* Secondly, we are using the MQTT Protocol as a transport layer to access the Kuzzle API. This means that the full Kuzzle API is available through this transport, but that the mechanism for handling requests and responses differs from that of the websocket transport. This is most evident for the Publish/Subscribe methods of the Kuzzle API. We need to distinguish between the subscription to the "Kuzzle/Response" MQTT topic, used only to communicate with the Kuzzle API, and a subscription to a Kuzzle Collection, used for pub/sub in the context of an application. To create a subscription to a Kuzzle Collection via the MQTT protocol, first we must publish a request to Kuzzle on the "Request" topic with action set to "subscribe", then we will receive a response on the "Kuzzle/response" MQTT topic containings the name of the MQTT topic that we need to subscribe to. Once the response is received on the "Kuzzle/response" MQTT topic, we can then subscribe to the  MQTT topic specified is response's topic field to listen for messages.

---
layout: full.html.hbs
algolia: true
title: Getting Started
order: 0
---

# Getting Started

Kuzzle has native support for the following network protocols: HTTP, Websocket and Socket.io.

However, any number of protocols can be implemented, adding new network capabilities.

Protocols are entirely responsible of the network communication layer, which can be as simple as a UDP socket, all the way to a complete pub/sub message broker.  
Protocols can even decide to propose a dedicated message format and/or query syntax for the Kuzzle API.

Protocols are provided with objects to interact with Kuzzle:
* [EntryPoint]({{ site_base_path }}protocols/1/entrypoint): base communication layer (declare user connections, forward API requests, ...)
* [context]({{ site_base_path }}protocols/1/context): utilities and object constructors not directly unrelated to network communications

Protocol implementation example: [MQTT](https://github.com/kuzzleio/protocol-mqtt)

--- 

## Prerequisites

### Location

Protocols are subdirectories that must be put at the following location: `<kuzzle_install_dir>/protocols/enabled`.

The recommended way to install a protocol is to put it in `protocols/available`, and then link it to the `protocols/enabled` directory.

### Node.js modules

Kuzzle loads protocols as [Node.js modules](https://nodejs.org/dist/latest-v8.x/docs/api/modules.html).

This means that a protocol directory must contain either:

* an `index.js` file 

and/or:

* a valid [package.json](https://docs.npmjs.com/files/package.json) file. If the protocol's entrypoint is not the root `index.js` file, then the [main](https://docs.npmjs.com/files/package.json#main) property must be filled

### manifest.json

Kuzzle needs a few informations to make your protocol work properly. These informations must be provided in a `manifest.json` file, in the protocol directory.

The following properties can be defined in this `manifest.json` file:

* `name` (**required**): protocol unique identifier. This protocol name will be used by Kuzzle for statistics, logs, and to provide context to requests
* `kuzzleVersion`: a non-empty string describing a [semver range](https://www.npmjs.com/package/semver#ranges), limiting the range of Kuzzle versions supported by this protocol. If not set, a warning is displayed on the console, and Kuzzle assumes that the protocol is only compatible with Kuzzle v1.x

---

## Interface

To add new network capabilities, a protocol must implement a set of functions, to be called by Kuzzle:

* [broadcast]({{ site_base_path }}protocols/1/essentials/broadcast)
* [disconnect]({{ site_base_path }}protocols/1/essentials/disconnect)
* [init]({{ site_base_path }}protocols/1/essentials/init)
* [joinChannel]({{ site_base_path }}protocols/1/essentials/joinchannel)
* [leaveChannel]({{ site_base_path }}protocols/1/essentials/leavechannel)
* [notify]({{ site_base_path }}protocols/1/essentials/notify)

If one or multiple of these functions are missing, Kuzzle fails to load the protocol, and refuses to start.

---

## Channels

`channel` is a recurrent concept in the protocol interface, one that must be implemented by the protocol itself.

Simply put: it is the same `channel` identifier returned to a user after a [real-time subscription]({{ site_base_path }}api/1/controller-realtime/subscribe/). Many users can share the same channel, as it is calculated from the provided subscription filters, after they are normalized (i.e. equivalent yet differently written filters still share the same identifier).

Kuzzle notifies protocols when one of their managed connection [joins]({{ site_base_path }}protocols/1/essentials/joinchannel) or [leaves]({{ site_base_path }}protocols/1/essentials/leavechannel) a channel.

Kuzzle has no opinion on how a protocol handles channels and their associated users. It simply asks protocols to [broadcast]({{ site_base_path }}protocols/1/essentials/broadcast), or to [notify]({{ site_base_path }}protocols/1/essentials/notify) messages to listening users.

--- 

## Configuration

Protocols can be configured in the Kuzzle [configuration]({{ site_base_path }}guide/essentials/configuration/) file, under the `server/protocols/<protocol name>` section.

### Example

```json
{
  "server": {
    "protocols": {
      "mqtt": {
        "port": 1883,
        "allowPubSub": true
      }
    }
  }
}
```

The custom configuration can be found in the EntryPoint object provided to the `init` function, under the following property: `entryPoint.config.protocols.<protocol name>`

---

## Protocol example

```javascript
module.exports = class MyProtocol {
  constructor () {
    this.context = null;
    this.entryPoint = null;
    this.name = 'foobar';
    
    // Example on how to maintain client connections
    this.clients = {};
    this.connections = {};
  }

  /** 
  * @param {EntryPoint} entryPoint - main protocol interface with Kuzzle
  * @param {object} context - Constructors and utilities
  */
  init (entryPoint, context) {
    // plugin initialization
    this.entryPoint = entryPoint;
    this.context = context;
    
    // user configuration can be retrieved from entryPoint.config[protocol name]
    this.config = Object.assign({
      default: 'value'
    }, entryPoint.config[this.name] || {});
  }

  /*
   This function is only an example showing how to interact with
   clients and with Kuzzle. It does not implement any actual protocol.

   The way a protocol plugins handles clients closely depends on the
   implemented protocol.
   */
  handleClient () {
    // when a client connects
    this.on('onClientConnect', client => {
      const connection = new context.constructor.ClientConnection(
        this.name, 
        [client.connection.stream.remoteAddress], 
        {some: 'header'}
      );

      this.entryPoint.newConnection(connection);
      this.clients[connection.id] = client;
      this.connections[client.id] = connection;
    });

    // when a client sends a request
    this.on('onClientRequest', (client, data) => {
      // Instantiates a Request object to be passed to Kuzzle
      const
        connection = this.connections[client.id],
        request = new this.context.Request(data, this.connections[client.id]);

      this.entryPoint.execute(request, response => {
        // forward the response to the client
      });
    });

    // whenever a client is disconnected
    this.on('onClientDisconnect', client => {
      const connection = this.connections[client.id];
      this.entryPoint.removeConnection(connection.id);
      delete this.clients[connection.id];
      delete this.connections[client.id];
    });
  }

  /*
   Invoked by Kuzzle when a "data.payload" payload needs to be
   broadcasted
  */
  broadcast (channels, payload) {
    for (const channel of channels) {
      // send the payload to all connections having subscribed
      // to that channel
    }
  }

  /*
   Invoked by Kuzzle when a payload needs to be sent to
   a single connection
  */
  notify (channels, connectionId, payload) {
    for (const channel of channels) {
      // send the payload to the connection 
    });
  }

  /*
    Invoked by Kuzzle when a connection has subscribed to a channel
   */
  joinChannel (channel, connectionId) {
     // ...
  }

  /*
    Invoked by Kuzzle when a connection leaves a channel
   */
  leaveChannel (channel, connectionId) {
    // ...
  }

  /*
    Invoked by Kuzzle when it needs to force-close a client connection
   */
  disconnect (connectionId) {
    const client = this.clients[connectionId];
    // close the client connection
  }
}
```

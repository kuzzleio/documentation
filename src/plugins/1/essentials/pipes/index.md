---
layout: full.html.hbs
algolia: true
title: Pipes
order: 300
---


# Pipes

Pipes are functions plugged to [events]({{ site_base_path }}plugins/1/events/), called synchronously by Kuzzle, and receiving information regarding that event.

Pipes can:

* Decide to abort a task. If a pipe throws an error, Kuzzle interrupts the task, and forwards a standardized version of the thrown error to the originating user
* Change the received information. Kuzzle will use the updated information upon resuming the task

<div class="alert alert-warning">If a pipe takes too long to respond, Kuzzle will eventually abort the entire task with a <a href="{{ site_base_path }}plugins/1/errors/gatewaytimeouterror">GatewayTimeout</a> error. The timeout value can be changed in the <a href="{{ site_base_path }}guide/1/essentials/configuration">configuration files.</a></div>



## Example

```javascript
module.exports = class PipePlugin {
  constructor () {}

  /*
    Required plugin initialization function
    (see the "Plugin prerequisites" section)
   */
  init (customConfig, context) {
    /*
      Attaches the plugin function "addCreatedAt" to the Kuzzle event
      "document:beforeCreate"
     */
    this.pipes = {
      'document:beforeCreate': 'addCreatedAt'
    };
  }

  // Called whenever "document:beforeCreate" is triggered
  addCreatedAt (request, callback) {
    request.input.body.createdAt = Date.now();
    callback(null, request);
  }
}
```

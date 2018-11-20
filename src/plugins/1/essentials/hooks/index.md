---
layout: full.html.hbs
algolia: true
title: Hooks
order: 200
---


# Hooks

Hooks are asynchronous listeners, plugged to [events]({{ site_base_path }}plugins/1/events), and receiving information regarding that event.

Hooks can only listen: the received information cannot be changed. And Kuzzle doesn't wait for their execution either, so hooks cannot change the outcome of whatever triggered the listened event.


## Example

```javascript
module.exports = class HookPlugin {
  constructor () {}

  /*
   Required plugin initialization function
   (see the "Plugin prerequisites" section)
   */
  init (customConfig, context) {
    /*
      Attaches the plugin function "myFunction" to the Kuzzle event
      "document:afterCreate", triggered after a successful
      document creation
     */
    this.hooks = {
      'document:afterCreate': 'myFunction'
    };
  }

  /*
   Called whenever the "document:afterCreate" event 
   is triggered
   */
  myFunction (request, event) {
    console.log(`Event ${event} triggered`);
    console.log(`Document created: ${request.result._id}`);
  }
}
```

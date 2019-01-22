---
layout: full.html.hbs
title: Hooks
order: 200
---

# Hooks

Hooks are asynchronous listeners, plugged to [events]({{ site_base_path }}plugins/1/events), and receiving information regarding that event.

Hooks can only listen: the received information cannot be changed. And Kuzzle doesn't wait for their execution either, so hooks cannot change the outcome of whatever triggered the listened event.

---

## Usage

Plugins can register hooks by exposing a `hooks` object: keys are listened [events]({{ site_base_path }}plugins/1/events), and values are either a function to execute whenever that event is triggered, or an array of functions.

```javascript
this.hooks = {
  '<kuzzle event to listen>': <function to call>,
  '<another event>': [list, of, functions, to call]
};
```

---

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
      Calls the plugin function "myFunction" when the Kuzzle event
      "document:afterCreate" is triggered after a successful
      document creation
     */
    this.hooks = {
      'document:afterCreate': (...args) => this.myFunction(...args)
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

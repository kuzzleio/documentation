---
layout: full.html.hbs
algolia: true
title: Hooks
order: 200
---

# Hooks

Hooks are asynchronous listeners, plugged to [events]({{ site_base_path }}kuzzle-events/1/), and receiving information regarding that event.

Hooks can only listen: the received information cannot be changed. And Kuzzle doesn't wait for their execution either, so hooks cannot change the outcome of whatever triggered the listened event.

---

## Usage

Plugins can register hooks by exposing a `hooks` object: keys are listened [events]({{ site_base_path }}kuzzle-events/1/), and values are either a function name to execute whenever that event is triggered, or an array of function names.

```javascript
this.hooks = {
  '<kuzzle event to listen>': '<plugin function name to call>',
  '<another event>': ['list', 'of', 'plugin', 'functions', 'to call']
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
      Attaches the plugin function "myFunction" to the Kuzzle event
      "eventType:hookName"
     */
    this.hooks = {
      'eventType:hookName': 'myFunction'
    };
  }

  /*
   Call whenever the "eventType:hookName" event 
   is triggered
   */
  myFunction (message, event) {
    console.log(`Event ${event} triggered`);
    console.log(`Message received: ${message}`);
  }
}
```

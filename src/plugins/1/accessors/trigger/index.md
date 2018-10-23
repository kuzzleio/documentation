---
layout: full.html.hbs
algolia: true
title: trigger
---

# trigger

{{{since "1.0.0"}}}

Triggers a custom event.

This allows interactions with other plugins using [hooks]({{ site_base_path }}plugins/1/essentials/hooks/) or [pipes]({{ site_base_path }}plugins/1/essentials/pipes/).

### Arguments

`trigger(event, [payload])`

* `event`: {string} custom event name
* `payload`: {optional, object} optional event payload


**Note:** the triggered event is renamed using the following format:<br/>`plugin-<plugin name>:<event>`. 

### Example

```js
// Emitting plugin, named "some-plugin"
context.accessors.trigger('someEvent', {
  someAttribute: 'someValue'
});

// Listening plugin
class ListeningPlugin {
  constructor () {
    this.hooks = {
      'plugin-some-plugin:someEvent': 'someEventListener'
    };
  }

  someEventListener (payload) {
    this.doSomething(payload);
  }
}
```

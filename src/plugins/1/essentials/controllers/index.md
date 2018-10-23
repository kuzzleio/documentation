---
layout: full.html.hbs
algolia: true
title: Controllers
order: 400
---

# Controllers

Kuzzle's API is divided into controllers, each exposing executable actions (see [API reference]({{ site_base_path }}api/1/query-syntax)).

Plugins can extend Kuzzle's API by adding new controllers to it. 

[Security access]({{ site_base_path }}guide/1/essentials/security/) to plugin controllers must be given (or denied), using the exact same way as with native API controllers.

---

## Querying plugins controllers

To avoid naming conflicts, Kuzzle prefixes plugin controllers names with the plugin name.

### HTTP

```http
URL: http://<server>:<port>/_plugin/<plugin name>/<url defined by the plugin>/<resources> 
Method: <verb defined by the plugin>
```

### Other protocols

```javascript
{
  "controller": "<plugin name>/<added controller name>",
  "action": "<action of the added controller>",
  ...
}
```

---

## Creating a Controller Route

In order to create a new controller, the plugin must expose the following properties:

- A `controllers` object, describing the new controller(s) to add. It will automatically be made available to any network protocol, except for HTTP
- A `routes` objects, describing how the new controller(s) should be exposed to the HTTP protocol
- The controller's actions, which are functions taking a `Request` object as an argument. These functions must return a promise, resolved with the action's result, or rejected with a [KuzzleError]({{ site_base_path }}plugins/1/plugins-context/errors/) object.

---

## Query normalization

Kuzzle normalizes [queries]({{ site_base_path }}api/1/query-syntax) into [Request]({{ site_base_path }}plugins/1/plugins-context/constructors/#request) objects.

Quick summary of how queries are normalized:

* HTTP:
  * dynamic arguments provided in the URL, and query string arguments are stored in `request.input.args`
  * the body content is made available in `request.input.body`
  * if the URL contains an `index`, a `collection` or a `_id` argument, it will be stored in `request.input.resource`
  * request headers can be found in `request.context.connection.misc.headers`

* Other protocols:
  * the `body` property is stored in `request.input.body`
  * these root properties are available in `request.input.resource`: `index`, `collection`, `_id`
  * any other properties at the root of the query object will be stored in `request.input.args`

---

## Automatic Events Generation

Kuzzle triggers events on all controller routes, including those added by plugins.

Read more about these automatic controller events [here]({{ site_base_path }}kuzzle-events/1/plugin-events/).

---

## Example

```javascript
module.exports = class ControllerPlugin {
  constructor () {
    /*
      Adds a new "newController" controller to Kuzzle's API.

      That new controller exposes 2 actions: myAction, and myOtherAction

      These actions point to functions exposed to Kuzzle by the plugin.

      Network protocols (other than HTTP) will be able to invoke this new
      controller with the following JSON object:

      {
        controller: '<plugin name>/newController',
        action: 'myAction',
        ...
      }
     */
    this.controllers = {
      newController: {
        myAction: 'actionFunction',
        myOtherAction: 'otherActionFunction'
      }
    };

    /*
      To expose the new controller, it must be registered to Kuzzle's HTTP router.

      To do so, a "routes" property must be exposed by the plugin, as an array
      of objects. Each object is an API route, with the following properties:

      - verb: HTTP verb
      - url: HTTP address. Any parameter starting with a ':'
        will be made dynamic by Kuzzle (its value is stored in request.input.args)
      - controller: plugin controller name, as exposed in the "this.controllers" object
      - action: controller action to execute     

      The first route exposes the following GET URL:
        http://<kuzzle server>:<port>/_plugin/<plugin name>/foo/<dynamic value>

      Kuzzle will call the function 'actionFunction' with a Request object,
      containing the "name" property: request.input.args.name = '<dynamic value>'

      The second route exposes the following POST URL:
        http://<kuzzle server>:<port>/_plugin/<plugin name>/bar

      Kuzzle will provide the content body of the request in the Request object
      passed to the function 'otherActionFunction', in the request.input.body
      property
     */
    this.routes = [
      {verb: 'get', url: '/foo/:name', controller: 'newController', action: 'myAction'},
      {verb: 'post', url: '/bar', controller: 'newController', action: 'myOtherAction'}
    ];
  }

  /*
    Required plugin initialization function
    (see the "Plugin prerequisites" section)
   */
  init (customConfig, context) {
    // plugin initialization
  }

  /*
    Implements the action newController/myAction
    Takes a Request object as an argument, and MUST return
    a promise resolved (or rejected) with the action's result
    This result can be of any JS type (scalar, object, array), and
    will be used to build a response to send to the requesting client
   */
  actionFunction (request) {
    // do action

    // optional: set network specific headers
    if (request.context.protocol === 'http') {
      // expires in 1h
      request.response.setHeader('expires', new Date(Date.now() + 3600000).toUTCString());
    }

    // Resolve with the result content. For instance:
    return Promise.resolve({acknowledge: true});
  }

  /*
    Implements the action newController/myOtherAction
    Takes a Request object as an argument, and MUST return
    a promise resolved (or rejected) with the action's result
    This result can be of any JS type (scalar, object, array), and
    will be used to build a response to send to the requesting client
   */
  otherActionFunction (request) {
    // do action
    return Promise.resolve(/* result content */);
  }
}
```

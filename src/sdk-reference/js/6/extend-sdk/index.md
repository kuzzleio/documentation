---
layout: sdk.html.hbs
title: Extends the SDK
description: Extends the SDK
order: 410
---

# Extends the SDK with a custom SDK controller

It is possible to extend the SDK's functionality by adding new controllers.  

These controllers generally correspond to [custom controllers created in a plugin]({{ site_base_path }}plugins/1/essentials/controllers) but not necessarily. Thus, it is possible to use the actions of its plugin in the SDK in the same way as the other actions of the Kuzzle API.  

## Define a custom SDK controller

A custom SDK controller is a class inheriting from the [BaseController]({{ site_base_path }}sdk-reference/js/6/base-controller) class and defining methods that correspond to API actions.  

This class is exposed alongside the other class of the SDK module.  

This controller can then be added to the SDK with the [Kuzzle.useController]({{ site_base_path }}sdk-reference/js/6/kuzzle/use-controller) method.

## Constructor

The constructor of a custom controller will be called by passing the SDK instance to it. It must call the parent constructor with this instance of the SDK and its name as defined in the API.  

So if my plugin is named `nyc-open-data-taxi` (name defined in the [manifest.json]({{ site_base_path }}plugins/1/essentials/getting-started/#prerequisites) file) and I have extended the API with the following controller:

```javascript
this.controllers = {
  taxi: {
    startDuty: request => this.startDuty(request)
  }
}
```

Then the constructor of the custom SDK controller must specify its name as follows:

```javascript
const { BaseController } = require('kuzzle-sdk');

class TaxiController extends BaseController {
  constructor (kuzzle) {
    super(kuzzle, 'nyc-open-data-plugin/taxi');
  }
}
```

The controller name will then be injected into the requests sent with the [BaseController.query]({{ site_base_path }}sdk-reference/js/6/base-controller/query) method.

## Define custom SDK controller action

Each action of your custom SDK controller is a method of the class.  

These methods can use the [BaseController.query]({{ site_base_path }}sdk-reference/js/6/base-controller/query) method which only overloads [Kuzzle.query]({{ site_base_path }}sdk-reference/js/6/kuzzle/query) by injecting the name of the custom SDK controller.  

Keeping the previous example in mind, we have:

```javascript
const { BaseController } = require('kuzzle-sdk');

class TaxiController extends BaseController {
  constructor (kuzzle) {
    super(kuzzle, 'nyc-open-data-plugin/taxi');
  }

  startDuty (driver) {
    const apiRequest = {
      action: 'startDuty',
      body: {
        driver
      }
    };

    return this.query(apiRequest)
      .then(response => response.result);
  }
}
```

## Add custom SDK controller to the SDK

Once you have defined your custom SDK controller, you can add it to the SDK with the [Kuzzle.useController]({{ site_base_path }}sdk-reference/js/6/kuzzle/use-controller) method.  

This method takes as parameter the class of your controller and the name of the accessor under which it will be available in the SDK.  

You can then use the actions of your plugins in the same way as the rest of the Kuzzle API by taking advantage of authentication, offline mode management, etc.  

```javascript
const 
  TaxiController = require('./taxiController'),
  { Kuzzle, WebSocket } = require('kuzzle-sdk');

const kuzzle = new Kuzzle(new WebSocket('localhost'));

kuzzle.useController(TaxiController, 'taxi');

await kuzzle.taxi.startDuty('lia meh ry');
```

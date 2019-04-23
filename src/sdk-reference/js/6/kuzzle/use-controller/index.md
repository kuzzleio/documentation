---
layout: sdk.html.hbs
title: useController
description: Adds a new controller to the SDK
---

# useController

Adds a new controller to the SDK. 

*See also:* 
  - *[Extends the SDK]({{ site_base_path }}sdk-reference/js/6/extend-sdk)*

## Arguments

```javascript
useController (ControllerClass, accessor);
```

<br/>

| Argument  | Type   | Description            |
| -------------- | --------- | ------------- |
| `ControllerClass` | <pre>Class</pre> | Controller class. Must inherits from [BaseController]({{ site_base_path }}sdk-reference/js/6/base-controller)    |
| `accessor` | <pre>string</pre> | Accessor name for the controller in the Kuzzle object |

## Returns

Returns the Kuzzle object.

## Usage

[snippet=use-controller]

---
layout: sdk.html.hbs
algolia: true
title: subscribe
description:
order: 200
---

# subscribe

{{{since Kuzzle "1.0.0"}}}

Subscribes by providing a set of filters: messages, document changes and, optionally, user events matching the provided filters will generate [real-time notifications]({{site_base_path}}api/1/notifications), sent to you in real-time by Kuzzle.

## Signature

```javascript
/**
* @param {string} index
* @param {string} collection
* @param {object} filters
* @param {Function} callback
* @param {object} [options]
* @returns {Promise.<object>}
 */
 subscribe (index, collection, filters, callback, options = null)
```

## Arguments

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| ``index`` | String | Index name    |
| ``collection`` | String | Collection name    |
| ``filters`` | object | Set of filters following [Koncorde syntax]({{site_base_path}}kuzzle-dsl/1/essential/koncorde) |
| ``callback`` | Function | Callback function to handle notifications |
| ``options`` | object | An object containing query options |

### **callback**

Callback function that will be called each time a new notifications is received.  
The callback will receive the [notifications object]({{site_base_path}}sdk-reference/js/6/essentials/realtime-notifications) as only argument.  

### **options**

Additional subscription options.

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `scope` | String | Subscribe to document entering or leaving the scope</br>Possible values: `all`, `in`, `out`, `none` | `all`  |
| `users` | String | Subscribe to users entering or leaving the room</br>Possible values: `all`, `in`, `out`, `none` | `none` |
| `volatile` | object | subscription information, used in [user join/leave notifications]({{site_base_path}}api/1/volatile-data) | `null` |

## Resolve

## Usage

*Simple subscription to document notifications*

[snippet=document-notifications]

*Subscription to document notifications with scope option*

[snippet=document-notifications-leave-scope]

*Subscription to message notifications*

[snippet=message-notifications]

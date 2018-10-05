---
layout: sdk.html.hbs
algolia: true
title: query
description: Base method to send API query to Kuzzle
order: 200
---

# query

Base method used to send queries to Kuzzle, following the [API Documentation]({{ site_base_path }}api-documentation).

<div class="alert alert-warning">
This is a low-level method, exposed to allow advanced SDK users to bypass high-level methods.
</div>

## Signature

```javascript
/**
 * @param {object} request
 * @param {object} [options]
 * @returns {Promise.<object>}
 */
query(request, (options = {}));
```

## Arguments

| Argument  | Type   | Description            | Required |
| --------- | ------ | ---------------------- | -------- |
| `request` | object | API request options    | yes      |
| `options` | object | Optional query options | no       |

### **request**

All properties necessary for the Kuzzle API can be added in the request object.  
The following properties are the most common.

| Property     | Type   | Description                               | Required |
| ------------ | ------ | ----------------------------------------- | -------- |
| `controller` | string | Controller name                           | yes      |
| `action`     | string | Action name                               | yes      |
| `body`       | object | Query body for this action                | no       |
| `index`      | string | Index name for this action                | no       |
| `collection` | string | Collection name for this action           | no       |
| `_id`        | string | id for this action                        | no       |
| `volatile`   | object | Additional informations to send to Kuzzle | no       |

### **options**

An `object` containing query options.

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | true    |

## Resolve

Resolve to the raw Kuzzle API response. See the [API Documentation]({{ site_base_path }}api-documentation).

## Usage

[snippet=query]

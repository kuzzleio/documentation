---
layout: sdk.html.hbs
algolia: true
title: query
description: Send a request to kuzzle api
---

# query

Base method used to send queries to Kuzzle, following the [API Documentation]({{ site_base_path }}api-documentation).

<div class="alert alert-warning">
This is a low-level method, exposed to allow advanced SDK users to bypass high-level methods.
</div>

## Arguments

```javascript
query(request, [options]);
```

<br/>

| Argument  | Type   | Description            |
| -------------- | --------- | ------------- |
| `request` | <pre>object</pre> | API request options    |
| `options` | <pre>object</pre> | Optional query options |

### **request**

All properties necessary for the Kuzzle API can be added in the request object.
The following properties are the most common.

| Property     | Type   | Description                               |
| -------------- | --------- | ------------- |
| `controller` | <pre>string</pre> | Controller name (mandatory)               |
| `action`     | <pre>string</pre> | Action name (mandatory)                   |
| `body`       | <pre>object</pre> | Query body for this action                |
| `index`      | <pre>string</pre> | Index name for this action                |
| `collection` | <pre>string</pre> | Collection name for this action           |
| `_id`        | <pre>string</pre> | id for this action                        |
| `volatile`   | <pre>object</pre> | Additional informations to send to Kuzzle |

### **options**

An `object` containing Query options

| Property     | Type<br/>(default)    | Description   |
| -------------- | --------- | ------------- |
| `queuable` | <pre>boolean</pre><br/>(`true`) | Make this request queuable or not |

## Resolve

Resolve to the raw Kuzzle API response.
See the [API Documentation]({{ site_base_path }}api-documentation).

## Usage

[snippet=query]

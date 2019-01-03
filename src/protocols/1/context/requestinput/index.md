---
layout: full.html.hbs
title: RequestInput
---

# RequestInput

Request input, normalizing a [Kuzzle API call]({{ site_base_path }}api/1/essentials/query-syntax/#other-protocols-default) in JSON format.

This is the class used to build the `input` property of any [Request]({{ site_base_path }}protocols/1/context/request) object.

Technical information: [github repository](https://github.com/kuzzleio/kuzzle-common-objects/blob/master/README.md#modelsrequestinput)

---

## Constructor

```js
new RequestInput(data)
```

<br/>

| Arguments | Type | Description |
|-----------|------|-------------|
| `data` | <pre>object</pre> | API request, in JSON format |

### data

The `data` object can contain the following properties:

| Properties | Type | Description |
|-----------|------|-------------|
| `_id` | <pre>string</pre> | Resource unique identifier |
| `action` | <pre>string</pre> | Invoked API controller's action |
| `body` | <pre>object</pre> | Request specific data (document content, search queries, ...) |
| `collection` | <pre>string</pre> | Data collection |
| `controller` | <pre>string</pre> | Invoked API controller |
| `index` | <pre>string</pre> | Data index |
| `jwt` | <pre>string</pre> |  Authentication token |
| `volatile` | <pre>object</pre> | Request [volatile data]({{ site_base_path }}api/1/essentials/volatile-data/) |
| `...` | <pre>*</pre> | Unrecognized properties are considered request specific, and stored in the `args` object property |

---

## Properties

| Properties | Type | Description |
|-----------|------|-------------|
| `action` | <pre>string</pre> | Invoked API controller's action |
| `args` | <pre>object</pre> | Request specific arguments |
| `body` | <pre>object</pre> | Request specific data |
| `controller` | <pre>string</pre> | Invoked API controller |
| `jwt` | <pre>string</pre> | Authentication token |
| `resource` | <pre>object</pre> | Stored resource target |
| `volatile` | <pre>object</pre> | Request [volatile data]({{ site_base_path }}api/1/essentials/volatile-data/) |

### resource

The `resource` property contains resources information:

| Properties | Type | Description |
|-----------|------|-------------|
| `_id` | <pre>string</pre> | Resource unique identifier |
| `collection` | <pre>string</pre> | Data collection |
| `index` | <pre>string</pre> | Data index |

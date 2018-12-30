---
layout: full.html.hbs
title: Request
---

# Request

{{{since "1.0.0"}}}

Object representation of a Kuzzle [API call]({{ site_base_path }}api/1/essentials/query-syntax), to be used with the [entryPoint.execute]({{ site_base_path }}protocols/1/entrypoint/execute) function.

That object is continuously updated to reflect the current state of the request, during its entire lifecycle.

For more information about this object, refer to its [technical documentation](https://github.com/kuzzleio/kuzzle-common-objects/blob/master/README.md#request).

---

## Response headers

Network protocol specific headers can be added to the response. If the protocol supports it, these headers are forwarded in the response sent to the client.

To customize the response content, read the [RequestResponse](https://github.com/kuzzleio/kuzzle-common-objects#requestresponse) documentation.

---

## Constructor

```js
new Request(data, [options])
```
<br/>

| Arguments | Type | Description |
|-----------|------|-------------|
| `data` | <pre>object</pre> | API call, following the same format than non-HTTP [API calls]({{ site_base_path }}api/1/essentials/query-syntax) |
| `options` | <pre>object</pre> | Additional request context |


### options

The `options` object can contain the following properties:

| Properties | Type | Description |
|-----------|------|-------------|
| `connection` | <pre>object</pre> | {{{since "1.4.1"}}} Connection information (see the <a href=https://github.com/kuzzleio/kuzzle-common-objects/blob/master/README.md#requestcontextconnection-object-format>connection</a> object documentation) |
| `connectionId` | <pre>string</pre> | {{{deprecated "1.4.1"}}} Connection unique identifier |
| `error` | <pre><a href={{ site_base_path }}protocols/1/context/errors>KuzzleError</a>,<br/>Error</pre> | Sets the request response with the provided error |
| `requestId` | <pre>string</pre> | User-defined request identifier |
| `result` | <pre>*</pre> | Sets the request response with the provided result, and the request status is set to `200` |
| `status` | <pre>integer</pre> | Request status, following the [HTTP error code](https://en.wikipedia.org/wiki/List_of_HTTP_status_codes) standard |

---

## Properties

Read-only:

| Properties | Type | Description |
|-----------|------|-------------|
| `context` | <pre><a href=https://github.com/kuzzleio/kuzzle-common-objects/blob/master/README.md#modelsrequestcontext>RequestContext</a></pre> | General request information (logged user, network information, ...) |
| `error` | <pre><a href={{ site_base_path }}protocols/1/context/errors>KuzzleError</a> | Request error |
| `input` | <pre><a href=https://github.com/kuzzleio/kuzzle-common-objects/blob/master/README.md#modelsrequestinput>RequestInput</a></pre> | Input request representation |
| `response` | <pre><a href=https://github.com/kuzzleio/kuzzle-common-objects#requestresponse>RequestResponse</a></pre> | Serialized [request response]({{ site_base_path }}api/1/essentials/kuzzle-response) |
| `result` | <pre>*</pre> | Request result |
| `timestamp` | <pre>integer</pre> | Request creation timestamp, in Epoch-millis format |

Writable:

| Properties | Type | Description |
|-----------|------|-------------|
| `id` | <pre>string</pre> | User-defined request unique identifier |
| `status` | <pre>integer</pre> | Request status code |

---

## clearError

{{{since "1.0.0"}}}

Clears the error: sets the `error` property to `null`, and the request status to `200`.

---

## serialize

{{{since "1.0.0"}}}

Serializes the request into into a pair of objects that can be sent across the network.

### Example

```js
const foo = request.serialize();
const bar = new context.Request(foo.data, foo.options);
```

---

## setError

{{{since "1.0.0"}}}

Adds an error to the request.

The request status is also updated to the error status.

### Argument

```js
setError(error)
```

<br/>

| Arguments | Type | Description |
|-----------|------|-------------|
| `error` | <pre><a href={{ site_base_path }}protocols/1/context/errors>KuzzleError</a>, Error</pre> | Request error |

If a `KuzzleError` object is provided, the request's status attribute is set to the error one.

Otherwise, the provided error is embedded into a [InternalError]({{ site_base_path }}protocols/1/context/errors/#internalerror-default) object, and the request status is set to 500.

---

## setResult

{{{since "1.0.0"}}}

Sets the request result.

### Arguments

```js
setResult(result, [options])
```

<br/>

| Arguments | Type | Description |
|-----------|------|-------------|
| `result` | <pre>*</pre> | Request result |
| `options` | <pre>optional, object</pre> | Optional result configuration |

#### options

The `options` object can contain the following properties:

| Properties | Type (default) | Description |
|-----------|------|-------------|
| `headers` | <pre>object</pre> | Network specific headers. Shortcut to the [response](https://github.com/kuzzleio/kuzzle-common-objects#requestresponse) header functions |
| `raw` | <pre>boolean (false)</pre> | If `true`, instead of a standard [kuzzle response]({{ site_base_path }}api/1/essentials/kuzzle-response), the result is sent as is to the client, without being interpreted |
| `status` | <pre>integer (200)</pre> | Request status |

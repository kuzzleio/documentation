---
layout: full.html.hbs
title: Request
---

# Request

{{{since "1.0.0"}}}

Object representation of a Kuzzle [API call]({{ site_base_path }}api/1/query-syntax).

That object is continuously updated to reflect the current state of the request, during its entire lifecycle.

For more information about this object, refer to its [technical documentation](https://github.com/kuzzleio/kuzzle-common-objects/blob/master/README.md#request).

---

## Response headers

Network protocol specific headers can be added to the response. If the protocol supports it, these headers are forwarded in the response sent to the client.

As Kuzzle supports the HTTP protocol natively, the Request object handles HTTP headers special cases.  
Other network protocols headers are stored in raw format, and protocols have to handle
their own specific headers manually.

To customize the response content, read the [RequestResponse](https://github.com/kuzzleio/kuzzle-common-objects#requestresponse) documentation.

---

## Constructor

{{{since "1.0.0"}}} / {{{deprecated "1.2.0"}}}

```js
new Request(request, data, [options])
```

{{{since "1.2.0"}}} 

```js
new Request(data, [options])
```

<br/>

| Arguments | Type | Description |
|-----------|------|-------------|
| `request` | <a href="#request-default"><pre>Request</pre></a> | A source request to inherit from |
| `data` | <pre>object</pre> | API call, following the same format than non-HTTP [API calls]({{ site_base_path }}api/1/query-syntax) |
| `options` | <pre>object</pre> | Additional request context |

### options

The `options` argument accepts the following parameters:

| Options | Type | Description |
|---------|------|-------------|
| `connection` | <pre>object</pre> | {{{since "1.4.1"}}} Connection information (see the <a href=https://github.com/kuzzleio/kuzzle-common-objects/blob/master/README.md#requestcontextconnection-object-format>connection</a> object documentation) |
| `connectionId` | <pre>string</pre> | {{{deprecated "1.4.1"}}} Connection unique identifier |
| `error` | <pre><a href={{ site_base_path }}plugins/1/errors>KuzzleError</a>, Error</pre> | Sets the request response with the provided error |
| `requestId` | <pre>string</pre> | User-defined request identifier |
| `result` | <pre>*</pre> | Sets the request response with the provided result, and the request status is set to `200` |
| `status` | <pre>integer</pre> | Request status, following the [HTTP error code](https://en.wikipedia.org/wiki/List_of_HTTP_status_codes) standard |

---

## Properties

Read-only:

| Property | Type | Description |
|----------|------|-------------|
| `context` | <pre><a href=https://github.com/kuzzleio/kuzzle-common-objects/blob/master/README.md#modelsrequestcontext>RequestContext</a></pre> | General request information (logged user, network information, ...) |
| `error` | <pre><a href={{ site_base_path }}plugins/1/errors>KuzzleError</a></pre> | Request error |
| `input` | <pre><a href=https://github.com/kuzzleio/kuzzle-common-objects/blob/master/README.md#modelsrequestinput>RequestInput</a></pre> | Input request representation |
| `response` | <pre><a href=https://github.com/kuzzleio/kuzzle-common-objects#requestresponse>RequestResponse</a></pre> | Serialized [request response]({{ site_base_path }}api/1/essentials/kuzzle-response) |
| `result` | <pre>*</pre> | Request result |
| `timestamp` | <pre>integer</pre> | Request creation timestamp, in Epoch-millis format |

Writable:

| Property | Type | Description |
|----------|------|-------------|
| `id` | <pre>string</pre> | User-defined request unique identifier |
| `status` | <pre>integer</pre> | Request status code |

---

## clearError

{{{since "1.0.0"}}}

Clears the error: sets the `error` property to `null`, and the request status to `200`.

---

## serialize

{{{since "1.0.0"}}}

Serializes the request into into a pair of objects that can be sent across the network, and then used to rebuild another equivalent `Request` object.

### Example

```js
const foo = request.serialize();
const bar = new context.constructors.Request(foo.data, foo.options);
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
| `error` | <pre><a href={{ site_base_path }}plugins/1/errors>KuzzleError</a></pre> | Request error |

If a `KuzzleError` object is provided, the request's status attribute is set to the error one.

Otherwise, the provided error is embedded into a [InternalError]({{ site_base_path }}plugins/1/errors/internalerror) object, and the request status is set to 500.

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
| `options` | <pre>object</pre> | Optional result configuration |


### options

The `options` argument accepts the following parameters:

| Options | Type (default) | Description |
|---------|------|-------------|
| `headers` | <pre>object (null)</pre> | Network specific headers. Shortcut to the [response](https://github.com/kuzzleio/kuzzle-common-objects#requestresponse) header functions |
| `raw` | <pre>boolean (false)</pre> | If `true`, instead of a standard [kuzzle response]({{ site_base_path }}api/1/essentials/kuzzle-response), the result is sent as is to the client, without being interpreted |
| `status` | <pre>integer (200)</pre> | Request status |

---
layout: full.html.hbs
algolia: true
title: Request
---

# Request

{{{since "1.0.0"}}}

Object representation of a Kuzzle [API call]({{ site_base_path }}api/1/query-syntax).

That object is continuously updated to reflect the current state of the request, during its entirely lifecycle.

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

`new Request(request, data, [options])`

{{{since "1.2.0"}}} 

`new Request(data, [options])`

* `request`: {optional, [Request](#request-default)} a source request to inherit from
* `data`: {objecŧ} API call, following the same format than non-HTTP [API calls]({{ site_base_path }}api/1/query-syntax)
* `options`: {optional, object} additional request context. The following options are supported:
  * {{{since "1.4.1"}}} <code>connection</code>: {object} connection information (see the <a href=https://github.com/kuzzleio/kuzzle-common-objects/blob/master/README.md#requestcontextconnection-object-format>connection</a> object documentation)
  * {{{deprecated "1.4.1"}}} <code>connectionId</code>: {string} connection unique identifier
  *  `error`: {[KuzzleError]({{ site_base_path }}plugins/1/errors)|`Error`} sets the request response with the provided error
  * `requestId`: {string} user-defined request identifier
  * `result`: sets the request response with the provided result, and the request status is set to `200`
  * `status`: {integer} request status, following the [HTTP error code](https://en.wikipedia.org/wiki/List_of_HTTP_status_codes) standard

---

## Properties

Read-only:

* `context`: {[RequestContext](https://github.com/kuzzleio/kuzzle-common-objects/blob/master/README.md#modelsrequestcontext)} general request information (logged user, network information, ...)
* `error`: {[KuzzleError]({{ site_base_path }}plugins/1/errors)|`null`} request error
* `input`: {[RequestInput](https://github.com/kuzzleio/kuzzle-common-objects/blob/master/README.md#modelsrequestinput)} input request representation
* `response`: {[RequestResponse](https://github.com/kuzzleio/kuzzle-common-objects#requestresponse)} serialized [request response]({{ site_base_path }}api/1/kuzzle-response)
* `result`: request result
* `timestamp`: {integer} request creation timestamp, in Epoch-millis format

Writable:

* `id`: {string} user-defined request unique identifier
* `status`: {integer} request status code

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

`setError(error)`

* `error`: {[KuzzleError]({{ site_base_path }}plugins/1/errors)|`null`} request error

If a `KuzzleError` object is provided, the request's status attribute is set to the error one.

Otherwise, the provided error is embedded into a [InternalError](https://github.com/kuzzleio/kuzzle-common-objects/blob/master/README.md#errorsinternalerror) object, and the request status is set to 500.

---

## setResult

{{{since "1.0.0"}}}

Sets the request result.

### Arguments

`setResult(result, [options])`

* `result`: request result
* `options`: {optional, object} optional result configuration:
  * `headers`: {object} network specific headers. Shortcut to the [response](https://github.com/kuzzleio/kuzzle-common-objects#requestresponse) header functions
  * `raw`: {boolean} if `true`, instead of a standard [kuzzle response]({{ site_base_path }}api/1/kuzzle-response), the result is sent as is to the client, without being interpreted (defaults to `false`)
  * `status`: {integer} request status (defaults to `200`)

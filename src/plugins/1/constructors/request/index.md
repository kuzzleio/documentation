---
layout: full.html.hbs
algolia: true
title: Request
---


# Request

{{{since "1.0.0"}}}

Object representation of a Kuzzle [API call]({{ site_base_path }}api/1/query-syntax).

That object is continuously updated to reflect the current state of the request, during its entire lifecycle.

For more information about this object, refer to its [technical documentation](https://github.com/kuzzleio/kuzzle-common-objects/blob/master/README.md#request).


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
|
## Properties

Read-only:

| Property | Type | Description |
|
## clearError

{{{since "1.0.0"}}}

Clears the error: sets the `error` property to `null`, and the request status to `200`.


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
|
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
| `raw` | <pre>boolean (false)</pre> | If `true`, instead of a standard [kuzzle response]({{ site_base_path }}api/1/kuzzle-response), the result is sent as is to the client, without being interpreted |
| `status` | <pre>integer (200)</pre> | Request status |

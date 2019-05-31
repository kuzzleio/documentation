---
code: true
type: page
title: RequestContext
---

# RequestContext

Connection context.

This is the class used to build the `context` property of any [Request](/core/1/protocols/context/request) object.

Technical information: [github repository](https://github.com/kuzzleio/kuzzle-common-objects/blob/master/README.md#modelsrequestcontext)

---

## Constructor

```js
new RequestContext([options]);
```

<br/>

| Arguments    | Type              | Description        |
| ------------ | ----------------- | ------------------ |
| \* `options` | <pre>object</pre> | Optional arguments |

### options

The `options` object can contain the following properties:

| Properties   | Type                                                                               | Description                      |
| ------------ | ---------------------------------------------------------------------------------- | -------------------------------- |
| `connection` | <pre><a href=/core/1/protocols/context/clientconnection>ClientConnection</a></pre> | Connection information           |
| `token`      | <pre>string</pre>                                                                  | Authorization token              |
| `user`       | <pre>object</pre>                                                                  | Kuzzle internal user information |

---

## Properties

| Properties   | Type                                                                               | Description                      |
| ------------ | ---------------------------------------------------------------------------------- | -------------------------------- |
| `connection` | <pre><a href=/core/1/protocols/context/clientconnection>ClientConnection</a></pre> | Connection information           |
| `token`      | <pre>string</pre>                                                                  | Authorization token              |
| `user`       | <pre>object</pre>                                                                  | Kuzzle internal user information |

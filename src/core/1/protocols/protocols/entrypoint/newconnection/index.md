---
code: true
type: page
title: newConnection
---

# newConnection



Declares a new client connection to Kuzzle.

---

### Arguments

```js
newConnection(connection);
```

<br/>

| Arguments    | Type                                                                               | Description         |
| ------------ | ---------------------------------------------------------------------------------- | ------------------- |
| `connection` | <pre><a href=/core/1/protocols/context/clientconnection>ClientConnection</a></pre> | New user connection |

---

### Example

```js
const conn = new context.ClientConnection('<protocol name>', ['127.0.0.1']);

entryPoint.newConnection(conn);
```

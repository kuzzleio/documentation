---
layout: full.html.hbs
algolia: true
title: newConnection
---

# newConnection

{{{since "1.0.0"}}}

Declares a new client connection to Kuzzle.

---

### Arguments

`newConnection(connection)`

* `connection`: {[`ClientConnection`]({{ site_base_path }}protocols/1/context/clientconnection)} new user connection

---

### Example

```js
const conn = new context.ClientConnection('<protocol name>', ['127.0.0.1']);

entryPoint.newConnection(conn);
```

---
layout: full.html.hbs
algolia: true
title: newConnection
---


# newConnection

{{{since "1.0.0"}}}

Declares a new client connection to Kuzzle.


### Example

```js
const conn = new context.ClientConnection('<protocol name>', ['127.0.0.1']);

entryPoint.newConnection(conn);
```

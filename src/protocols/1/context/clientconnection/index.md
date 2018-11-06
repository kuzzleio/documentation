---
layout: full.html.hbs
algolia: true
title: ClientConnection
---

# ClientConnection

{{{since "1.0.0"}}}

The `ClientConnection` class must be instantiated whenever a new client connection is created, and that instance must be provided to the [entryPoint.newConnection]({{ site_base_path }}protocols/1/entrypoint/newconnection) method.

---

## Arguments

```js
new context.ClientConnection(protocol, ips, headers)
```


<br/>

| Arguments | Type | Description |
|-----------|------|-------------|
| `protocol` | <pre>string</pre> | The protocol unique identifier |
| `ips` | <pre>string[]</pre> | List of forwarded ip addresses (or any client connection information) of the connection |
| `headers` | <pre>object</pre> | Extra connection information |

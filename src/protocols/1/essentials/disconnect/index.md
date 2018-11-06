---
layout: full.html.hbs
algolia: true
title: disconnect
---

# disconnect

Asks the protocol to force-close a connection.

---

## Arguments

```js
disconnect(connectionId)
```

<br/>

| Arguments | Type | Description |
|-----------|------|-------------|
| `connectionId` | <pre>string</pre> | Connection unique identifier, previously registered by the protocol using [newConnection]({{ site_base_path }}protocols/1/entrypoint/newconnection) |

---

## Return

The `disconnect` function is not expected to return a value.

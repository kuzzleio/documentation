---
layout: full.html.hbs
algolia: true
title: joinChannel
algolia: true
---

# joinChannel

Informs the protocol that one of its connected users joined a [channel]({{ site_base_path }}protocols/1/essentials/getting-started/#channels-default).

---

## Arguments

```js
joinChannel(channel, connectionId)
```

<br/>

| Arguments | Type | Description |
|-----------|------|-------------|
| `channel` | <pre>string</pre> | Joined channel identifier |
| `connectionId` | <pre>string</pre> | Connection unique identifier, previously registered by the protocol using [newConnection]({{ site_base_path }}protocols/1/entrypoint/newconnection) |

---

## Return

The `joinChannel` function is not expected to return a value.

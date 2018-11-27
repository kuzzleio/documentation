---
layout: full.html.hbs
title: leaveChannel
---

# leaveChannel

Informs the protocol that one of its connected users left a [channel]({{ site_base_path }}protocols/1/essentials/getting-started/#channels-default).

---

## Arguments

```js
leaveChannel(channel, connectionId)
```

<br/>

| Arguments | Type | Description |
|-----------|------|-------------|
| `channel` | <pre>string</pre> | Left channel identifier |
| `connectionId` | <pre>string</pre> | Connection unique identifier, previously registered by the protocol using [newConnection]({{ site_base_path }}protocols/1/entrypoint/newconnection) |

---

## Return

The `leaveChannel` function is not expected to return a value.

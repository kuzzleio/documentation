---
layout: full.html.hbs
algolia: true
title: broadcast
---

# broadcast

Asks the protocol to emit a payload to [channels]({{ site_base_path }}protocols/1/essentials/getting-started/#channels-default).

---

## Arguments

```js
broadcast(channels, payload)
```

<br/>

| Arguments | Type | Description |
|-----------|------|-------------|
| `channels` | <pre>string[]</pre> | List of channels |
| `payload` | <pre>object</pre> | Data payload |

---

## Return

The `broadcast` function is not expected to return a value.

---
layout: sdk.html.hbs
title: RemoveListener
description: Removes a channel from an event
---

# RemoveListener

Removes a channel from an event.

## Arguments

```js
RemoveListener(event int, channel chan<- interface{})
```

<br/>

| Argument   | Type     | Description      |
| ---------- | -------- | -------- |
| `event`    | <pre>int</pre> | Event constant from the `event` package |
| `channel` | <pre>channel</pre> | Channel to unregister |

## Usage

[snippet=remove-listener]

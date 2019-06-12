---
code: true
type: page
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
| `event`    | `int` | Event constant from the `event` package |
| `channel` | `channel` | Channel to unregister |

## Usage

<<< ./snippets/remove-listener.go

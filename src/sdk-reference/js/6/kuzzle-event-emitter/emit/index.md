---
layout: sdk.html.hbs
title: emit
description: Emit an event
---

# emit

Emits an event with the specified payload.


## Signature

```js
emit (eventName, ...payload);
```

## Arguments

| Argument   | Type     | Description      |
| ---------- | -------- | -------- |
| `eventName`    | <pre>string</pre> | The name of the event |
| `payload` | <pre>any</pre> | Payload(s) to send with the event     |

## Return

The `KuzzleEventEmitter` instance.

## Usage

[snippet=emit]

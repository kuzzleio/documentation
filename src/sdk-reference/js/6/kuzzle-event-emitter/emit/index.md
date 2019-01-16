---
layout: sdk.html.hbs
title: emit
description: Emits an event
---

# emit

Emits an event with the specified payload.


## Arguments

```js
emit (eventName, ...payload);
```

<br/>

| Argument   | Type     | Description      |
| ---------- | -------- | -------- |
| `eventName`    | <pre>string</pre> | One of the event described in the [Events]({{ site_base_path }}sdk-reference/js/6//events) section of this documentation |
| `payload` | <pre>any</pre> | Payload(s) to send with the event     |

## Return

The `KuzzleEventEmitter` instance.

## Usage

[snippet=emit]

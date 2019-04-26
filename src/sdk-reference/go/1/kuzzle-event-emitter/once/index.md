---
layout: sdk.html.hbs
title: Once
description: Adds a one-time channel for an event
---

# Once

Adds a **one-time** channel to an event. 

The next time the event is triggered, this channel is removed and then fed.

Whenever an event is triggered, channels are fed in the order they were registered.

Channels removed this way are **not** closed.

## Arguments

```js
Once(event int, channel chan<- interface{})
```

<br/>

| Argument   | Type     | Description      |
| ---------- | -------- | -------- |
| `event`    | <pre>int</pre> | Event constant from the `kuzzle.event` package |
| `channel` | <pre>channel</pre> | Event payload channel |


## Usage

[snippet=once]

---
code: true
type: page
title: stopQueuing
description: Stops the requests queuing
---

# stopQueuing

Stops the requests queuing.  
Works only in `offline` state, and if the `autoQueue` option is set to false.

## Signature

```java
io.kuzzle.sdk.Kuzzle stopQueuing()
```

## Return

The `io.kuzzle.sdk.Kuzzle` instance.

## Usage

<<< ./snippets/stop-queuing.java

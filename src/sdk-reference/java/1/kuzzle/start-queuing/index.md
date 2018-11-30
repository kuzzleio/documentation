---
layout: sdk.html.hbs
title: startQueuing
description: Starts the requests queuing
---

# startQueuing

Starts the requests queuing.  
Works only in `offline` state, and if the `autoQueue` option is set to false.

## Signature

```java
io.kuzzle.sdk.Kuzzle startQueuing()
```

## Return

The `io.kuzzle.sdk.Kuzzle` instance.

## Usage

[snippet=start-queuing]

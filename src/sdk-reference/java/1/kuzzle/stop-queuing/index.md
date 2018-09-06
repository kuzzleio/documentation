---
layout: sdk.html
algolia: true
title: stopQueuing
description: Stops the requests queuing
order: 200
---

# stopQueuing

Stops the requests queuing.  
Works only in `offline` state, and if the `autoQueue` option is set to false.

## Signature

```java
Kuzzle stopQueuing()
```

## Return

The `Kuzzle` instance.

## Usage

[snippet=stop-queuing]

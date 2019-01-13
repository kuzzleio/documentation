---
layout: sdk.html.hbs
title: stopQueuing
description: Stops the requests queuing
---

# stopQueuing

Stops the requests queuing.  
Works only in `offline` state, and if the `autoQueue` option is set to false.

## Signature

```csharp
public Kuzzle stopQueuing();

```

## Return

The `Kuzzle` instance.

## Usage

[snippet=stop-queuing]
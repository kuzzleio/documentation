---
layout: sdk.html.hbs
title: startQueuing
description: Starts the requests queuing
---

# startQueuing

Starts the requests queuing.  
Works only in `offline` state, and if the `autoQueue` option is set to false.

## Signature

```csharp
public Kuzzle startQueuing();
```

## Return

The `Kuzzle` instance.

## Usage

[snippet=start-queuing]

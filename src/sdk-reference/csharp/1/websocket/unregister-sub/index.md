---
layout: sdk.html.hbs
title: unregisterSub
description: Removes a subscription's notifications listener.
---

# registerSub

Removes a subscription's notifications listener.

## Signature

```csharp
public override void unregisterSub(string arg0);

```

## Arguments

| Argument   | Type                      | Description
| ---------- |---------------------------|-------------------------------------------------- |
| `room_id` | <pre>string</pre>  | Subscription unique identifier

### listener

A c++11 lambda which takes a `string` argument.
`EventListener` is defined as `std::function<void(string)>`.

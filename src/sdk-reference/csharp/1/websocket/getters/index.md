---
layout: sdk.html.hbs
title: Getters
description: Getters for WebSocket class
order: 100
---

# WebSocket class getters

## isAutoReconnect

Returns the auto-reconnection flag value.

## Signature

```csharp
public bool isAutoReconnect();

```

## isAutoResubscribe

Returns the auto-resubscribe flag value.

## Signature

```csharp
public bool isAutoResubscribe();

```

## getPort

Returns the port number used by the protocol instance.

## Signature

```csharp
public uint getPort();

```

## getReconnectionDelay

Returns the reconnection delay used by the protocol instance.

## Signature

```csharp
public ulong getReconnectionDelay();

```

## isSslConnection

Returns a bool indicating is the protocol instance is using SSL or not.

## Signature

```csharp
public bool isSslConnection();

```

## Usage

[snippet=getters]

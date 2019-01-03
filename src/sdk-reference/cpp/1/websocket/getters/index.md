---
layout: sdk.html.hbs
title: Getters
description: Getters for WebSocket class
order: 100
---

# WebSocket class getters

## isAutoReconnect

Returns the auto-reconnection flag value.

### Signature

```cpp
bool isAutoReconnect()
```

## isAutoResubscribe

Returns the auto-resubscribe flag value.

### Signature

```cpp
bool isAutoResubscribe()
```

## getPort

Returns the port number used by the protocol instance.

### Signature

```cpp
unsigned int getPort()
```

## getReconnectionDelay

Returns the reconnection delay used by the protocol instance.

### Signature

```cpp
uint64_t getReconnectionDelay()
```

## isSslConnection

Returns a boolean indicating is the protocol instance is using SSL or not.

### Signature

```cpp
bool isSslConnection()
```

## Usage

[snippet=getters]

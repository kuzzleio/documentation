---
layout: sdk.html.hbs
title: Getters
description: Getters for Kuzzle class
order: 100
---

# Kuzzle class getters

## getJwt

Returns the JWT token currently used to authenticate requests.

### Arguments

```cpp
std::string getJwt();
```

## getProtocol

Returns the protocol instance used internally to communicate with the Kuzzle server.

### Arguments

```cpp
Protocol* getProtocol();
```

## getQueueMaxSize

Returns the number of maximum requests kept during offline mode.

### Arguments

```cpp
int getQueueMaxSize();
```

## getQueueTTL

Returns the time a queued request is kept during offline mode, in milliseconds.

### Arguments

```cpp
int getQueueTTL();
```

## getReplayInterval

Returns delay between each replayed requests.

### Arguments

```cpp
int getReplayInterval();
```

## getReconnectionDelay

Returns the time between each reconnection attempt, in milliseconds.

### Arguments

```cpp
int getReconnectionDelay();
```

## getVolatile

Returns the JSON string representing volatile data sent with each request.

### Arguments

```cpp
std::string getVolatile();
```

## isAutoQueue

Returns true or false if queries are automatically queued during offline mode.

### Arguments

```cpp
bool isAutoQueue();
```

## isAutoReconnect

Returns true or false if it will automatically reconnect after a connection loss.

### Arguments

```cpp
bool isAutoReconnect();
```

## isAutoReplay

Returns true or false if automatically replay queued requests on a reconnected event.

### Arguments

```cpp
bool isAutoReplay();
```

## isAutoResubscribe

Returns true or false if automatically renew all subscriptions on a reconnected event.

### Arguments

```cpp
bool isAutoResubscribe();
```

## Usage

[snippet=getters]

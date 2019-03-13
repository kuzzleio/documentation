---
layout: sdk.html.hbs
title: Getters
description: Getters for Kuzzle class
order: 100
---

# Kuzzle class getters

## autoQueue

Returns true or false if queries are automatically queued during offline mode.

### Arguments

```cpp
bool autoQueue();
```

## autoReconnect

Returns true or false if it will automatically reconnect after a connection loss.

### Arguments

```cpp
bool autoReconnect();
```

## autoReplay

Returns true or false if automatically replay queued requests on a reconnected event.

### Arguments

```cpp
bool autoReplay();
```

## autoResubscribe

Returns true or false if automatically renew all subscriptions on a reconnected event.

### Arguments

```cpp
bool autoResubscribe();
```

## getProtocol

Returns the protocol instance used internally to communicate with the Kuzzle server.

### Arguments

```cpp
Protocol* getProtocol();
```

## jwt

Returns the JWT token currently used to authenticate requests.

### Arguments

```cpp
std::string jwt();
```

## queueMaxSize

Returns the number of maximum requests kept during offline mode.

### Arguments

```cpp
int queueMaxSize();
```

## queueTTL

Returns the time a queued request is kept during offline mode, in milliseconds.

### Arguments

```cpp
int queueTTL();
```

## replayInterval

Returns delay between each replayed requests.

### Arguments

```cpp
int replayInterval();
```

## reconnectionDelay

Returns the time between each reconnection attempt, in milliseconds.

### Arguments

```cpp
int reconnectionDelay();
```

## volatiles

Returns the JSON string representing volatile data sent with each request.

### Arguments

```cpp
std::string volatiles();
```

## Usage

[snippet=getters]

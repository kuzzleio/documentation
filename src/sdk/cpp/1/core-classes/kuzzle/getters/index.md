---
code: true
type: page
title: Getters
description: Getters for Kuzzle class
order: 100
---

# Kuzzle class getters

## autoQueue

Returns a boolean telling if queries are automatically queued during offline mode.

### Arguments

```cpp
bool autoQueue() const noexcept;
```

## autoReconnect

Returns a boolean telling if it will automatically reconnect after a connection loss.

### Arguments

```cpp
bool autoReconnect() const noexcept;
```

## autoReplay

Returns a boolean telling if it automatically replays queued requests on a `reconnected` event.

### Arguments

```cpp
bool autoReplay() const noexcept;
```

## autoResubscribe

Returns a boolean telling if it automatically renews all subscriptions on a `reconnected` event.

### Arguments

```cpp
bool autoResubscribe() const noexcept;
```

## getProtocol

Returns the protocol instance used internally to communicate with the Kuzzle server.

### Arguments

```cpp
Protocol* getProtocol();
```

## jwt

Returns the JWT currently used to authenticate requests.

### Arguments

```cpp
const std::string jwt() const noexcept;
```

## queueMaxSize

Returns the number of maximum requests kept during offline mode.

### Arguments

```cpp
int queueMaxSize() const noexcept;
```

## queueTTL

Returns the time a queued request is kept during offline mode, in milliseconds.

### Arguments

```cpp
int queueTTL() const noexcept;
```

## replayInterval

Returns the delay between each replayed requests.

### Arguments

```cpp
int replayInterval() const noexcept;
```

## reconnectionDelay

Returns the time between each reconnection attempt, in milliseconds.

### Arguments

```cpp
int reconnectionDelay() const noexcept;
```

## volatiles

Returns the JSON string representing volatile data sent with each request.

### Arguments

```cpp
const std::string& volatiles() const noexcept;
```

## Usage

<<< ./snippets/getters.cpp

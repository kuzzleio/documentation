---
layout: sdk.html.hbs
title: Getters
description: Getters for Kuzzle class
order: 100
---

# Kuzzle class getters

## getProtocol

Returns the protocol instance used internally to communicate with the Kuzzle server.

### Signature

```cpp
Protocol* getProtocol()
```

## getJwt

Returns the JWT token currently used to authenticate requests.

### Signature

```cpp
std::string getJwt()
```

## getVolatile

Returns the JSON string representing volatile data sent with each request.

### Signature

```cpp
std::string getVolatile()
```

## getListeners

Returns a map containing the listeners registered on [SDK event]({{ site_base_path }}sdk-reference/cpp/1/events).

### Signature

```cpp
std::map<int, EventListener*> getListeners()
```

## Usage

[snippet=getters]

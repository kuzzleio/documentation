---
layout: sdk.html.hbs
title: Getters
description: Getters for Kuzzle class
order: 100
---

# Kuzzle class getters

## getProtocol

Returns the protocol instance used internally to communicate with the Kuzzle server.

### Arguments

```cpp
Protocol* getProtocol();
```

## getJwt

Returns the JWT token currently used to authenticate requests.

### Arguments

```cpp
std::string getJwt();
```

## getVolatile

Returns the JSON string representing volatile data sent with each request.

### Arguments

```cpp
std::string getVolatile();
```

## Usage

[snippet=getters]

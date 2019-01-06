---
layout: sdk.html.hbs
title: Getters
description: Getters for Kuzzle class
order: 100
---

# Kuzzle class getters

## getProtocol

Returns the protocol instance used internally to communicate with the Kuzzle server.

## Signature

```csharp
public Protocol getProtocol();

```

## getJwt

Returns the JWT token currently used to authenticate requests.

## Signature

```csharp
public string getJwt();

```

## getVolatile

Returns the JSON string representing volatile data sent with each request.

## Signature

```csharp
public string getVolatile();

```

## getListeners

Returns a map containing the listeners registered on [SDK event]({{ site_base_path }}sdk-reference/csharp/1/events).

## Signature

```csharp
public SWIGTYPE_p_std__mapT_int_std__functionT_void_fstd__string_constF_t_const_p_std__lessT_int_t_t getListeners();

```

## Usage

[snippet=getters]

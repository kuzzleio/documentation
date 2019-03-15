---
layout: sdk.html.hbs
title: Setters
description: Setters for Kuzzle class
order: 200
---

# Kuzzle class setters

## setAutoReplay

Set the value for the auto-replay flag which allow to play queued request after a successfull auto-reconnection.

### Signature

```cpp
Kuzzle* setAutoReplay(bool);
```

## setVolatile

Set the value of the global [volatile data]({{ site_base_path }}sdk-reference/cpp/1/kuzzle/introduction#volatile-data-default).  
Value must be a JSON string representing a JSON object. (eg: `{"username": "Aschen"}`)

### Signature

```cpp
Kuzzle* volatiles(const std::string&);
```

## Usage

[snippet=setters]

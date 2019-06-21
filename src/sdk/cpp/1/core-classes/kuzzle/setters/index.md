---
code: true
type: page
title: Setters
description: Setters for Kuzzle class
order: 200
---

# Kuzzle class setters

## autoReplay

Set the value for the auto-replay flag which allow to play queued request after a successfull auto-reconnection.

### Signature

```cpp
Kuzzle* autoReplay(bool);
```

## volatiles

Set the value of the global [volatile data](/sdk/cpp/1/core-classes/kuzzle/#volatile-data).  
Value must be a JSON string representing a JSON object. (eg: `{"username": "Aschen"}`)

### Signature

```cpp
Kuzzle* volatiles(const std::string&);
```

## Usage

<<< ./snippets/setters.cpp

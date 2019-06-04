---
code: true
type: page
title: connect
description: Connects the SDK to Kuzzle
---

# connect

Connects to Kuzzle using the `Protocol` argument provided in the constructor options.
Subsequent call have no effect if the SDK is already connected.

## Signature

```cpp
void connect();
```

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle error](/sdk/cpp/1/essentials/error-handling).

## Usage

<<< ./snippets/connect.cpp

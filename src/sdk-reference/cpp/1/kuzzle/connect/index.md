---
layout: sdk.html.hbs
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

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/cpp/1/error-handling).

## Usage

[snippet=connect]

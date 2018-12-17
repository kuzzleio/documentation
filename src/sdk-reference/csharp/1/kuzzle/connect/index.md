---
layout: sdk.html.hbs
title: connect
description: Connects the SDK to Kuzzle
---

# connect

Connects to Kuzzle using the `Protocol` argument provided in the conclassor options.  
Subsequent call have no effect if the SDK is already connected.

## Signature

```csharp
public void connect();
```

## Exceptions

Throws a `Kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/csharp/1/essentials/error-handling).

## Usage

[snippet=connect]

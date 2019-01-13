---
layout: sdk.html.hbs
title: connect
description: Connects the SDK to Kuzzle
---

# connect

Connects to Kuzzle using the `Protocol` argument provided in theructor options.  
Subsequent call have no effect if the SDK is already connected.

## Signature

```csharp
public void connect();

public void disconnect();

```

## Exceptions

Throws a `Kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/csharp/1/error-handling).

## Usage

[snippet=connect]
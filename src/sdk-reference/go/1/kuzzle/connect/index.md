---
layout: sdk.html.hbs
title: connect
description: Connects the SDK to Kuzzle
---

# connect

Connects to Kuzzle using the `host` argument provided to the `connection.Connection` (see [Kuzzle constructor]({{ site_base_path }}sdk-reference/go/1/kuzzle/constructor#usage-go)).  
Subsequent call have no effect if the SDK is already connected.

## Signature

```go
Connect() error
```

## Return

Return a [Kuzzle error]({{ site_base_path }}sdk-reference/go/1/error-handling) if the SDK can not connect to Kuzzle.

## Usage

[snippet=connect]

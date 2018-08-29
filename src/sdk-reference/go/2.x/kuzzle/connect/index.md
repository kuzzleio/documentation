---
layout: sdk.html
algolia: true
title: connect
description: Connects the SDK to Kuzzle
order: 200
---

# connect

Connects to Kuzzle using the `host` argument provided to the `connection.Connection` (see [Kuzzle constructor]({{ site_base_path }}sdk-reference/kuzzle/constructor#usage-go)).  
Subsequent call have no effect if the SDK is already connected.  

## Signature

```go
Connect() error
```

## Return

Return a [Kuzzle error]({{ site_base_path }}sdk-reference/essentials/error-handling) if the SDK can not connect to Kuzzle.

## Usage

[code-example=connect]


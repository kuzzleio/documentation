---
layout: sdk.html.hbs
algolia: true
title: connect
description: Connects the SDK to Kuzzle
---

# connect

Connects to Kuzzle using the `host` property provided in the [constructor options]({{ site_base_path }}sdk-reference/js/6/kuzzle/constructor/#arguments-default).
Subsequent call have no effect if the SDK is already connected.

## Arguments

```javascript
connect ()
```

## Resolve

Resolve if connection is made successfully.

## Usage

[snippet=connect]

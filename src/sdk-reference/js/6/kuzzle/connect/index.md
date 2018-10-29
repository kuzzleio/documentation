---
layout: sdk.html.hbs
algolia: true
title: connect
description: Connects the SDK to Kuzzle
order: 200
---

# connect

Connects to Kuzzle using the `host` property provided in the [constructor options]({{ site_base_path }}sdk-reference/js/6/kuzzle/constructor/#arguments-default).  
Subsequent call have no effect if the SDK is already connected.

## Signature

```javascript
/**
 * @returns {Promise<>}
 */
connect ()
```

## Resolve

Resolve if connection is made successfully.

## Usage

[snippet=connect]

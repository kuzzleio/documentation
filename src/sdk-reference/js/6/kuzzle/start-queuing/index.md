---
layout: sdk.html.hbs
algolia: true
title: startQueuing
description: Starts the requests queuing
---

# startQueuing

Starts the requests queuing.  
Works only in `offline` state, and if the `autoQueue` option is set to false.

## Signature

```javascript
/**
 * @returns {Kuzzle} this
 */
startQueuing ()
```

## Return

The `Kuzzle` instance.

## Usage

[snippet=start-queuing]

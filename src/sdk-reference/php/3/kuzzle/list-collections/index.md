---
layout: sdk.html.hbs
algolia: true
title: listCollections
description: Kuzzle:listCollections
---

  

# listCollections
Returns the list of known data collections contained in a specified index.


## Options

| Option | Type | Description | Default |
|
## Callback Response

Returns an array of JSON objects containing the list of stored and/or realtime collections on the provided index.

## Usage

[snippet=list-collections-1]
> Callback response:

```json
[
  {"name": "realtime_1", "type": "realtime"},
  {"name": "realtime_2", "type": "realtime"},
  {"name": "realtime_...", "type": "realtime"},
  {"name": "realtime_n", "type": "realtime"},
  {"name": "stored_1", "type": "stored"},
  {"name": "stored_2", "type": "stored"},
  {"name": "stored_...", "type": "stored"},
  {"name": "stored_n", "type": "stored"}
]
```
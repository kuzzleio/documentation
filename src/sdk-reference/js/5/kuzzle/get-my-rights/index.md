---
layout: sdk.html.hbs
algolia: true
title: getMyRights
description: Kuzzle:getMyRights
---

  

# getMyRights
Gets the rights for the current user.


## Options

| Option | Type | Description | Default |
|
## Callback Response

Returns an array of rights.

## Usage

[snippet=get-my-rights-1]
> Callback response

```json
[
  {
    "controller": "my-controller",
    "action": "my-action",
    "index": "*",
    "collection": "*",
    "value": "allowed"
  },
  {
    "controller": "another-controller",
    "action": "*",
    "index": "my-index",
    "collection": "*",
    "value": "conditional"
  }
]
```
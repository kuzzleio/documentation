---
layout: sdk.html.hbs
algolia: true
title: getUserRights
description: Security:getUserRights
---

  

# getUserRights
> Callback response example

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

Given a Kuzzle user id (`kuid`), retrieves the list of permissions granted to that user.


## Options

| Option | Type | Description | Default |
|
### Callback Response

Returns an array of objects.
## Usage

[snippet=get-user-rights-1]

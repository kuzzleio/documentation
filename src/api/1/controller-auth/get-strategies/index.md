---
layout: full.html.hbs
algolia: true
title: getStrategies
---


# getStrategies

{{{since "1.0.0"}}}

Gets the exhaustive list of registered authentication strategies.


## Response

The result is an array of available strategy names:

```javascript
{
  "status": 200,
  "error": null,
  "action": "getStrategies",
  "controller": "auth",
  "result": [
    "local",
    "facebook"
  ]
}
```

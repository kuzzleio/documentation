---
layout: full.html.hbs
algolia: true
title: mDeleteProfiles
---


# mDeleteProfiles

{{{since "1.0.0"}}}

Deletes multiple security profiles.


## Arguments

### Optional:

* `refresh`: if set to `wait_for`, Kuzzle will not respond until the deletions are indexed


## Response

Returns an array of successfully deleted profiles.

```javascript
{
  "status": 200,
  "error": null,
  "action": "mDeleteProfiles",
  "controller": "security",
  "requestId": "<unique request identifier>",
  "result": [
    "profile1",
    "profile2",
    "..."
  ]
}
```

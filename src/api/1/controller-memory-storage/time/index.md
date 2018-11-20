---
layout: full.html.hbs
algolia: true
title: time
---


# time

{{{since "1.0.0"}}}

Returns the current server time.

[[_Redis documentation_]](https://redis.io/commands/time)


## Response
 
Returns the time as a two items array: 

* a timestamp in [Epoch time](https://en.wikipedia.org/wiki/Unix_time) 
* the number of microseconds already elapsed in the current second

### Example

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "time",
  "collection": null,
  "index": null,
  "result": [
    "1538640821",
    "450704"
  ]
}
```

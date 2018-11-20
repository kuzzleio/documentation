---
layout: full.html.hbs
algolia: true
title: srandmember
---


# srandmember

{{{since "1.0.0"}}}

Returns one or more members of a set of unique values, at random.  

[[_Redis documentation_]](https://redis.io/commands/srandmember)


## Arguments

* `_id`: set key identifier

### Optional:

* `count`: return `count` members, at random (default: `1`). If positive, then returned values are unique. If negative, a set member can be returned multiple times.

---

## Response

If `count` is not set or equal to `1`, returns the member as a string:

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "srandmember",
  "collection": null,
  "index": null,
  "result": "<random member>"
}
```

If the absolute count value is greater than `1`, then an array of random members is returned instead:

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "srandmember",
  "collection": null,
  "index": null,
  "result": [
    "random member 1",
    "random member 2", 
    "..."
  ]
}
```

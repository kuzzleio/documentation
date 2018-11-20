---
layout: full.html.hbs
algolia: true
title: sort
---


# sort

{{{since "1.0.0"}}}

Sorts and returns elements contained in a list, a set of unique values or a sorted set.  
By default, sorting is numeric and elements are compared by their value, interpreted as double precision floating point number.

[[_Redis documentation_]](https://redis.io/commands/sort)


## Arguments

* `_id`: list, set or sorted set key identifier


## Response

Returns the sorted elements.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "sort",
  "collection": null,
  "index": null,
  "result": [
    "sorted element1",
    "sorted element2",
    "..."
  ]
}
```

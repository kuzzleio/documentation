---
layout: full.html.hbs
algolia: true
title: lrem
---



# lrem

{{{since "1.0.0"}}}

Removes the first occurences of an element from a list.

[[_Redis documentation_]](https://redis.io/commands/lrem)


## Argument

* `_id`: list key identifier


## Response

Returns the number of removed elements.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "lrem",
  "collection": null,
  "index": null,
  "result": 2
}
```

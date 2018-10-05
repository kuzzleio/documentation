---
layout: full.html.hbs
algolia: true
title: list
---

# list

{{{since "1.0.0"}}}

Return the complete list of data indexes.

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/_list
Method: GET
```

### Other protocols


```js
{
  "controller": "index",
  "action": "list"
}
```

---

## Response

Return a `indexes` array listing all existing index names.

```js
{
  "status": 200,
  "error": null,
  "controller": "index",
  "action": "list",
  "requestId": "<unique request identifier>",
  "result": {
    "indexes": [
      "index_1",
      "index_2",
      "index_...",
      "index_n"
    ]
  }
}
```

---

## Possible errors

- [Common errors]({{ site_base_path }}api/1/errors/#common-errors)

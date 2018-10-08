---
layout: full.html.hbs
algolia: true
title: mDeleteUsers
---

# mDeleteUsers

{{{since "1.0.0"}}}

Delete multiple users.

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/users/_mDelete[?refresh=wait_for]
Method: POST  
Body:
```

```js
{
  "ids": ["kuid1", "kuid2", "..."]
}
```

### Other protocols

```js
{
  "controller": "security",
  "action": "mDeleteUsers",
  "body": {
    "ids": ["kuid1", "kuid2", "..."]
  }
}
```

---

## Arguments

### Optional:

* `refresh`: if set to `wait_for`, Kuzzle will not respond until the deletions are indexed

---

## Body properties

* `ids`: an array of user [kuids]({{site_base_path}}guide/1/kuzzle-depth/authentication/#the-kuzzle-user-identifier) to delete

---

## Response

Return an array of successfully deleted users.

```javascript
{
  "status": 200,
  "error": null,
  "action": "mDeleteUsers",
  "controller": "security",
  "requestId": "<unique request identifier>",
  "result": [
    "kuid1",
    "kuid2", 
    "..."
   ]
  }
}
```

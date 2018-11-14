---
layout: sdk.html.hbs
algolia: true
title: setContent
description: User:setContent
---
  

# setContent

[snippet=set-content-1]
Replaces the content of User.

<aside class="note">
Updating a user will have no impact until the <a href="{{ site_base_path }}sdk-reference/user/create"><code>create</code></a> or <a href="{{ site_base_path }}sdk-reference/user/replace"><code>replace</code></a> method is called
</aside>

---

## setContent(data)

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| ``data`` | JSON Object |  User content |

---

## Return Value

Returns the `User` object.

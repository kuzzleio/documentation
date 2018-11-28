---
layout: sdk.html.hbs
title: setContent
description: User:setContent
---
  

# setContent
Replaces the content of User.

<div class="alert alert-info">
Updating a user will have no impact until the <a href="{{ site_base_path }}sdk-reference/user/create"><code>create</code></a> or <a href="{{ site_base_path }}sdk-reference/user/replace"><code>replace</code></a> method is called
</div>

---

## setContent(data)

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| ``data`` | JSON Object |  User content |

---

## Return Value

Returns the `User` object.

## Usage

[snippet=set-content-1]

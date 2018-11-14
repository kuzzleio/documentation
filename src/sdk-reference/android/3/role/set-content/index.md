---
layout: sdk.html.hbs
algolia: true
title: setContent
description: Role:setContent
---
  

# setContent
[snippet=set-content-1]
Replaces the content of the `Role` object.

<aside class="note">
Updating a role content will have no impact until the <a href="{{ site_base_path }}sdk-reference/role/save">save</a> method is called
</aside>

---

## setContent(data)

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| ``data`` | JSON Object | Role content |

---

## Return Value

Returns the `Role` object.

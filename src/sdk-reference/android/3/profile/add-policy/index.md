---
layout: sdk.html.hbs
algolia: true
title: addPolicy
description: Profile:addPolicy
---
  

# addPolicy
[snippet=add-policy-1]

Adds a role to the security profile.

<aside class="note">
Updating a security profile will have no impact until the <a href="{{ site_base_path }}sdk-reference/profile/save">save</a> method is called
</aside>

---

## addPolicy(id)

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| ``id`` | string | Unique id of the new role to associate |

---

## addPolicy(policy)

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| ``policy`` | JSON Object | policy instance corresponding to the new associated role and its restrictions |

---

## Return Value

Returns the `Profile` object to allow chaining calls.

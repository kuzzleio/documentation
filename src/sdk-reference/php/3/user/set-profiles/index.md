---
layout: sdk.html.hbs
algolia: true
title: setProfiles
description: User:setProfiles
---
  

# setProfiles
[snippet=set-profiles-1]

Replaces the security profiles linked to the user.

<aside class="note">
Updating a user will have no impact until the <code>create</code> or <code>replace</code> method is called
</aside>
---

## setProfiles(profileIds)
| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| ``profileIds`` | array of strings | List of profile IDs |

---

## setProfiles(profiles)

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| ``profiles`` | array of Profile objects | An array of instantiated [Profile]({{ site_base_path }}sdk-reference/profile) objects |

---

## Return Value

Returns the `User` object.

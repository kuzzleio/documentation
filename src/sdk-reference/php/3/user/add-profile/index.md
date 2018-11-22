---
layout: sdk.html.hbs
algolia: true
title: addProfile
description: User:addProfile
algolia: true
---
  

# addProfile
Replaces the security profile associated with the user.

<aside class="note">
Updating a user will have no impact until the <a href="{{ site_base_path }}sdk-reference/user/create"><code>create</code></a> or <a href="{{ site_base_path }}sdk-reference/user/replace"><code>replace</code></a> method is called
</aside>

---

## addProfile(profileId)

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| ``profileId`` | string | Profile ID |

---

## addProfile(profile)

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| ``profile`` | Profile | An instantiated [Profile]({{ site_base_path }}sdk-reference/profile) object |

---

## Return Value

Returns the `User` object.

## Usage

[snippet=add-profile-1]

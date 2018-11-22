---
layout: sdk.html.hbs
algolia: true
title: addProfile
description: User:addProfile
---
  

# addProfile
Replaces the security profile associated with the user.

<div class="alert alert-info">
Updating a user will have no impact until the <a href="{{ site_base_path }}sdk-reference/user/create"><code>create</code></a> or <a href="{{ site_base_path }}sdk-reference/user/replace"><code>replace</code></a> method is called
</div>

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

---
code: true
type: page
title: addProfile
description: User:addProfile
---

# addProfile

Replaces the security profile associated with the user.

<div class="alert alert-info">
Updating a user will have no impact until the <a href="/sdk/php/3/core-classes/user/create/"><code>create</code></a> or <a href="/sdk/php/3/core-classes/user/replace/"><code>replace</code></a> method is called
</div>

---

## addProfile(profileId)

| Arguments   | Type   | Description |
| ----------- | ------ | ----------- |
| `profileId` | string | Profile ID  |

---

## addProfile(profile)

| Arguments | Type    | Description                                          |
| --------- | ------- | ---------------------------------------------------- |
| `profile` | Profile | An instantiated [Profile](/sdk/php/3/profile) object |

---

## Return Value

Returns the `User` object.

## Usage

<<< ./snippets/add-profile-1.php

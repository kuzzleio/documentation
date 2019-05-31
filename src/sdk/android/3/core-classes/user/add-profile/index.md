---
code: true
type: page
title: addProfile
description: User:addProfile
---

# addProfile

Replaces the security profile associated with the user.

<div class="alert alert-info">
Updating a user will have no impact until the <a href="/sdk/android/3/controllers/user/create/"><code>create</code></a> or <a href="/sdk/android/3/controllers/user/replace/"><code>replace</code></a> method is called
</div>

---

## addProfile(profileId)

| Arguments   | Type   | Description |
| ----------- | ------ | ----------- |
| `profileId` | string | Profile ID  |

---

## addProfile(profile)

| Arguments | Type    | Description                                                           |
| --------- | ------- | --------------------------------------------------------------------- |
| `profile` | Profile | An instantiated [Profile](/sdk/android/3/controllers/profile/) object |

---

## Return Value

Returns the `User` object.

## Usage

<<< ./snippets/add-profile-1.java

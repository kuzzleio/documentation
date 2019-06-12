---
code: true
type: page
title: addProfile
description: User:addProfile
---

# addProfile

Replaces the security profile associated with the user.

<div class="alert alert-info">
Updating a user will have no impact until the [`create`](/sdk/android/3/core-classes/user/create/) or [`replace`](/sdk/android/3/core-classes/user/replace/) method is called
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
| `profile` | Profile | An instantiated [Profile](/sdk/android/3/core-classes/profile/) object |

---

## Return Value

Returns the `User` object.

## Usage

<<< ./snippets/add-profile-1.java

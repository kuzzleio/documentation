---
code: true
type: page
title: setContent
description: User:setContent
---

# setContent

Replaces the content of User.

:::info
Updating a user will have no impact until the [create](/sdk/java/2/core-classes/user/create/) or [replace](/sdk/java/2/core-classes/user/replace/) method is called.
:::

---

## setContent(data)

| Arguments | Type        | Description  |
| --------- | ----------- | ------------ |
| `data`    | JSON Object | User content |

---

## Return Value

Returns the `User` object.

## Usage

<<< ./snippets/set-content-1.java

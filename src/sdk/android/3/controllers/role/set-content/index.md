---
code: true
type: page
title: setContent
description: Role:setContent
---

# setContent

Replaces the content of the `Role` object.

<div class="alert alert-info">
Updating a role content will have no impact until the <a href="/sdk/android/3/controllers/role/save/">save</a> method is called
</div>

---

## setContent(data)

| Arguments | Type        | Description  |
| --------- | ----------- | ------------ |
| `data`    | JSON Object | Role content |

---

## Return Value

Returns the `Role` object.

## Usage

<<< ./snippets/set-content-1.java

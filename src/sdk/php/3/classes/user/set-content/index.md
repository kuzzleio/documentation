---
code: true
type: page
title: setContent
description: User:setContent
---

# setContent

Replaces the content of User.

<div class="alert alert-info">
Updating a user will have no impact until the <a href="/sdk/php/3/classes/user/create/"><code>create</code></a> or <a href="/sdk/php/3/classes/user/replace/"><code>replace</code></a> method is called
</div>

---

## setContent(data)

| Arguments | Type        | Description  |
| --------- | ----------- | ------------ |
| `data`    | JSON Object | User content |

---

## Return Value

Returns the `User` object.

## Usage

<<< ./snippets/set-content-1.php

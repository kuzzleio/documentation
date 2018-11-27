---
layout: sdk.html.hbs
algolia: true
title: whoAmI
description: Kuzzle:whoAmI
algolia: true
---
  

# whoAmI
Returns informations about the user who is currently loggedin.

---

## whoAmI(callback)

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| ``callback`` | function | Callback handling the response |

---

## Callback Response

Returns an instantiated [User]({{ site_base_path }}sdk-reference/user) object.

## Usage

[snippet=who-am-i-1]

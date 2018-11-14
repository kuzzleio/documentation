---
layout: sdk.html.hbs
algolia: true
title: logout
description: Kuzzle:logout
---
  

# logout
[snippet=logout-1]
Logs the user out.

<aside class="notice">
This method is non-queuable, meaning that during offline mode, it will be discarded and the callback will be called with an error.
</aside>

---

## logout([callback])

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| ``callback`` | function | Optional callback handling the response |

This method empties the `jwtToken` property

---

## Return value

Returns the `Kuzzle` SDK object to allow chaining.

---

## Callback Response

Returns the `Kuzzle` SDK object once the logout process is complete, either successfully or not.  
The `Kuzzle` SDK object will unset the `jwtToken` property if the user is successfully logged out.

---
layout: sdk.html.hbs
title: setCredentials
description: User:setCredentials
---
  

# setCredentials
Sets the user's credentials.

<div class="alert alert-info">
  Updating user credentials will have no impact until the <a href="{{ site_base_path }}sdk-reference/android/3/user/create"><code>create</code></a> method is called.<br />
  The credentials to send depend on the authentication plugin and the strategy you want to create credentials for.
</div>
---

## setCredentials(credentials)

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| ``credentials`` | object | An object containing an attribute for each strategy you want to link the user to. |

---

## Return Value

Returns the `User` object.

## Usage

[snippet=set-credentials-1]

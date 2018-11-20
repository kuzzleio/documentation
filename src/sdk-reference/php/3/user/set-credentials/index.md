---
layout: sdk.html.hbs
algolia: true
title: setCredentials
description: User:setCredentials
---

  

# setCredentials
Sets the user's credentials.

<aside class="note">
  Updating user credentials will have no impact until the <a href="{{ site_base_path }}sdk-reference/user/create"><code>create</code></a> method is called.<br />
  The credentials to send depend on the authentication plugin and the strategy you want to create credentials for.
</aside>

## Return Value

Returns the `User` object.

## Usage

[snippet=set-credentials-1]

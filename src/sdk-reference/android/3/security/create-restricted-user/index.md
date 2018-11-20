---
layout: sdk.html.hbs
algolia: true
title: createRestrictedUser
description: Security:createRestrictedUser
---

  

# createRestrictedUser
Create a new restricted user in Kuzzle.  
This function allows anonymous users to create a "restricted" user with predefined rights.

<aside class="notice">
There is a small delay between user creation and its availability in our search layer (usually a couple of seconds).
That means that a user that was just created may not be returned by the <code>searchUsers</code> function at first.
</aside>


## Options

| Filter | Type | Description | Default |
|
## Callback response

Resolves to a [User]({{ site_base_path }}sdk-reference/user) object.

## Usage

[snippet=create-restricted-user-1]

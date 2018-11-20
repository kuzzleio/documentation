---
layout: sdk.html.hbs
algolia: true
title: createUser
description: Security:createUser
---

  

# createUser
Create a new user in Kuzzle.

<aside class="notice">
There is a small delay between user creation and its availability in our search layer (usually a couple of seconds).
That means that a user that was just created may not be returned by the <code>searchUsers</code> function at first.
</aside>


## Options

| Filter | Type | Description | Default |
|
## Callback Response

Returns a [User]({{ site_base_path }}sdk-reference/user) object.

## Usage

[snippet=create-user-1]

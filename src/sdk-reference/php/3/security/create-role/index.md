---
layout: sdk.html.hbs
algolia: true
title: createRole
description: Security:createRole
---

  

# createRole
Create a new role in Kuzzle.

<aside class="notice">
There is a small delay between role creation and its availability in our search layer (usually a couple of seconds).
That means that a role that was just created may not be returned by the <code>searchRole</code> function at first.
</aside>

## Options

| Filter | Type | Description | Default |
|
## Callback Response

Returns a [Role]({{ site_base_path }}sdk-reference/role) object.

## Usage

[snippet=create-role-1]

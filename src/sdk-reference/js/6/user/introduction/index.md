---
layout: sdk.html.hbs
title: Introduction
description: User class
order: 0
---

# User

This class represents a Kuzzle User.  

Refer to the [Security guide]({{ site_base_path }}guide/1/essentials/security) for more informations about users.

The following methods returns a `User`:
 - [auth:getCurrentUser]({{ site_base_path }}sdk-reference/js/6/auth/getCurrentUser)
 - [auth:updateSelf]({{ site_base_path }}sdk-reference/js/6/auth/updateSelf)

## Properties

Available properties.

| Property | Type | Description | Writable? |
|--- |--- |--- | :-------: |
| `_id` | <pre>string</pre> | User ID (kuid) |    No     |
| `content` | <pre>object</pre> | User internal content |    No     |

The `content` property is an object that contain generic properties alongside custom defined properties.

| Property | Type | Description | Writable? |
|--- |--- |--- | :-------: |
| `profileIds` | <pre>string[]</pre> | Profiles IDs for this user |    No     |
| `_kuzzle_info` | <pre>object</pre> | [Kuzzle metadata]({{ site_base_path }}guide/1/essentials/document-metadata) |    No     |

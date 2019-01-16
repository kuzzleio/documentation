---
layout: sdk.html.hbs
title: Introduction
description: Profile class
order: 0
---

# Profile

This class represents a Kuzzle Profile.  

Refer to the [Security guide]({{ site_base_path }}guide/1/essentials/security#defining-profiles-default) for more information about profiles.

## Properties

Available properties:

| Property | Type | Description | Writable? |
|--- |--- |--- | :-------: |
| `_id` | <pre>string</pre> | Profile ID |    No     |
| `policies` | <pre>object[]</pre> | Array of policies for this profile |    No     |

Each policy object can contain the following properties:

| Property | Type | Description | Writable? |
|--- |--- |--- | :-------: |
| `roleId` | <pre>string</pre> | Role identifier |    No     |
| `restrictedTo` | <pre>object[]</pre> | Array of object containing indexes and collections which the profile is restricted to |    No     |

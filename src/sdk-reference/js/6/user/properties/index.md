---
layout: sdk.html.hbs
title: Properties
description: User class properties
order: 100
---

# Properties


| Property | Type | Description |
|--- |--- |--- |
| `_id` | <pre>string</pre> | User ID (kuid) |
| `content` | <pre>object</pre> | User internal content |

### content

The `content` property is an object containing, alongside custom defined values, the following generic properties:

| Property | Type | Description |
|--- |--- |--- |
| `profileIds` | <pre>string[]</pre> | Profiles IDs for this user |
| `_kuzzle_info` | <pre>object</pre> | [Kuzzle metadata]({{ site_base_path }}guide/1/essentials/document-metadata) |

---
layout: full.html.hbs
algolia: true
title: auth:strategyAuthenticated
---


# auth:strategyAuthenticated

{{{since "1.0.0"}}}

| Arguments | Type | Description |
|
## user

The provided `user` object has the following properties:

| Properties | Type | Description |
|-----------|------|-------------|
| `_id` | <pre>string</pre> | User's [kuid]({{ site_base_path }}guide/1/essentials/user-authentication/#kuzzle-user-identifier-kuid) |
| `profileIds` | <pre>string[]</pre> | List of associated profiles |


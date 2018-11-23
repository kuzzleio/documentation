---
layout: full.html.hbs
algolia: true
title: auth:strategyAuthenticated
algolia: true
---

# auth:strategyAuthenticated

{{{since "1.0.0"}}}

| Arguments | Type | Description |
|-----------|------|-------------|
| `strategy` | <pre>string</pre> | [Authentication strategy]({{ site_base_path }}guide/1/essentials/user-authentication/#authentication-strategies-default) name |
| `user` | <pre>object</pre> | Authenticated user properties |

This event is triggered after a successful user authentication, but before a token is generated.

It is also triggered before the [auth:afterLogin]({{ site_base_path }}plugins/1/events/api-events/#after-default) event.

---

## user

The provided `user` object has the following properties:

| Properties | Type | Description |
|-----------|------|-------------|
| `_id` | <pre>string</pre> | User's [kuid]({{ site_base_path }}guide/1/essentials/user-authentication/#kuzzle-user-identifier-kuid) |
| `profileIds` | <pre>string[]</pre> | List of associated profiles |


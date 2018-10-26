---
layout: full.html.hbs
algolia: true
title: auth:strategyAuthenticated
---

# auth:strategyAuthenticated

{{{since "1.0.0"}}}

This event is triggered after a successful user authentication, but before a token is generated.

It is also triggered before the [auth:afterLogin]({{ site_base_path }}plugins/1/events/api-events/#after-default) event.

---

## Payload

* `strategy`: {string} [authentication strategy]({{ site_base_path }}guide/1/essentials/user-authentication/#authentication-strategies-default) name 
* `user`: {object} describes the authenticated user, with the following properties:
  * `_id`: {string} user's [kuid]({{ site_base_path }}guide/1/essentials/user-authentication/#kuzzle-user-identifier-kuid)
  * `profileIds`: {[Profile]({{ site_base_path }}guide/1/essentials/security/#users-profiles-and-roles)[]} list of associated profiles


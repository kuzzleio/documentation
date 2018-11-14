---
layout: sdk.html.hbs
algolia: true
title: getUserRights
description: Security:getUserRights
---
  

# getUserRights

[snippet=get-user-rights-1]
> Callback response example

[snippet=get-user-rights-2]

Given a Kuzzle user id (`kuid`), retrieves the list of permissions granted to that user.

---

### getUserRights(id, [options], callback)

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| ``kuid`` | String | [Kuzzle User Unique Identifier]({{ site_base_path }}guide/essentials/user-authentication/#kuzzle-user-identifier-kuid) |
| ``options`` | JSON Object | Optional parameters |
| ``callback`` | function | Callback handling the response |

---

## Options

| Option | Type | Description | Default |
|---------------|---------|----------------------------------------|---------|
| ``queuable`` | boolean | Make this request queuable or not  | ``true`` |

---

### Callback Response

Returns an array of objects.


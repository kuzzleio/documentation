---
layout: sdk.html.hbs
algolia: true
title: fetchRole
description: Security:fetchRole
---
  

# fetchRole
[snippet=fetch-role-1]

Fetches a single stored role using its unique ID.

---

## fetchRole(id, [options], callback)

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| ``id`` | string | Unique role identifier |
| ``options`` | JSON Object | Optional parameters |
| ``callback`` | function | Callback handling the response |

---

## Options

| Option | Type | Description | Default |
|---------------|---------|----------------------------------------|---------|
| ``queuable`` | boolean | Make this request queuable or not  | ``true`` |

---

## Callback Response

Returns a [Role]({{ site_base_path }}sdk-reference/role) object.

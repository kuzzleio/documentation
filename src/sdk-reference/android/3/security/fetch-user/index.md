---
layout: sdk.html.hbs
title: fetchUser
description: Security:fetchUser
---
  

# fetchUser
Fetches a single stored user using its unique ID.

---

## fetchUser(id, [options], callback)

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| ``id`` | string | Unique user identifier |
| ``options`` | JSON Object | Optional parameters |
| ``callback`` | function | Callback handling the response |

---

## Options

| Option | Type | Description | Default |
|---------------|---------|----------------------------------------|---------|
| ``queuable`` | boolean | Make this request queuable or not  | ``true`` |

---

## Callback Response

Returns a [User]({{ site_base_path }}sdk-reference/android/3/user) object.

## Usage

[snippet=fetch-user-1]

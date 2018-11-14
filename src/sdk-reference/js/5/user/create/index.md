---
layout: sdk.html.hbs
algolia: true
title: create
description: User:create
---
  

# create

[snippet=create-1]
Create the user in Kuzzle. Credentials can be created during the process by using [setCredentials]({{ site_base_path }}sdk-reference/user/set-credentials) beforehand.

---

## create([options], [callback])

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| ``options`` | JSON Object | Optional parameters |
| ``callback`` | function | (Optional) Callback handling the response |

---

## Options

| Option | Type | Description | Default |
|---------------|---------|----------------------------------------|---------|
| ``queuable`` | boolean | Make this request queuable or not  | ``true`` |

---

## Return Value

Returns the `User` object to allow chaining.

---

## Callback Response

Returns a `User` object.

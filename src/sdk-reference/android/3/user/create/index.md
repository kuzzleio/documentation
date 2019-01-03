---
layout: sdk.html.hbs
title: create
description: User:create
---
  

# create
Create the user in Kuzzle. Credentials can be created during the process by using [setCredentials]({{ site_base_path }}sdk-reference/android/3/user/set-credentials) beforehand.

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

## Usage

[snippet=create-1]

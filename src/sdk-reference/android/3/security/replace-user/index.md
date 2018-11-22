---
layout: sdk.html.hbs
algolia: true
title: replaceUser
description: Security:replaceUser
algolia: true
---
  

# replaceUser
Replaces an existing user.

---

## replaceUser(id, content, [options], [callback])

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| ``id`` | string | Unique user identifier |
| ``content`` | JSON Object | A plain JSON object representing the user, should contain the mandatory ``profileIds`` field |
| ``options`` | string | (Optional) Optional arguments |
| ``callback`` | function | (Optional) Callback handling the response |

---

## Options

| Filter | Type | Description | Default |
|---------------|---------|----------------------------------------|---------|
| ``queuable`` | boolean | Make this request queuable or not  | ``true`` |
| ``refresh`` | string | If set to ``wait_for``, Kuzzle will wait for the persistence layer to finish indexing (available with Elasticsearch 5.x and above) | ``undefined`` |

---

## Return Value

Returns the `Security` object to allow chaining.

---

## Callback Response

Returns a [User]({{ site_base_path }}sdk-reference/user) object.

## Usage

[snippet=replace-user-1]

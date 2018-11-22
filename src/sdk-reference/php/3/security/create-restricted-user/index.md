---
layout: sdk.html.hbs
algolia: true
title: createRestrictedUser
description: Security:createRestrictedUser
---
  

# createRestrictedUser
Create a new restricted user in Kuzzle.  
This function allows anonymous users to create a "restricted" user with predefined rights.

<div class="alert alert-info">
There is a small delay between user creation and its availability in our search layer (usually a couple of seconds).
That means that a user that was just created may not be returned by the <code>searchUsers</code> function at first.
</div>

---

## createRestrictedUser(id, content, [options], [callback])

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| ``id`` | string | Unique user identifier, will be used as username |
| ``content`` | JSON Object | A plain JSON object representing the user |
| ``options`` | string | (Optional) Optional arguments |
| ``callback`` | function | Callback handling the response |

---

## Options

| Filter | Type | Description | Default |
|---------------|---------|----------------------------------------|---------|
| ``queuable`` | boolean | Make this request queuable or not  | ``true`` |
| ``refresh`` | string | If set to ``wait_for``, Kuzzle will wait the persistence layer indexation to return (available with Elasticsearch 5.x and above) | ``undefined`` |

---

## Callback response

Resolves to a [User]({{ site_base_path }}sdk-reference/user) object.

## Usage

[snippet=create-restricted-user-1]

---
layout: sdk.html.hbs
algolia: true
title: deleteUser
description: Security:deleteUser
---
  

# deleteUser
[snippet=delete-user-1]

> Callback response
Delete the provided user.

<aside class="notice">
There is a small delay between the time a user is deleted and it being reflected in the search layer (usually a couple of seconds).
That means that a user that has just been deleted may still be returned by the <code>searchUsers</code> function at first.
</aside>

---

## deleteUser(id, [options], [callback])

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| ``id`` | string | Unique user identifier to delete |
| ``options`` | JSON Object | Optional parameters |
| ``callback`` | function | (Optional) Callback handling the response |

---

## Options

| Option | Type | Description | Default |
|---------------|---------|----------------------------------------|---------|
| ``queuable`` | boolean | Make this request queuable or not  | ``true`` |
| ``refresh`` | string | If set to ``wait_for``, Kuzzle will wait the persistence layer to finish indexing (available with Elasticsearch 5.x and above) | ``undefined`` |

---

## Return Value

Returns the `Security` object to allow chaining.

---

## Callback Response

Return the id of the user that has been deleted.

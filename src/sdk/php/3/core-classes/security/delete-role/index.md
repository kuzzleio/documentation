---
code: true
type: page
title: deleteRole
description: Security:deleteRole
---

# deleteRole

Delete the provided role.

:::info
There is a small delay between the time a role is deleted and it being reflected in the search layer (usually a couple of seconds).
That means that a role that was just deleted might still be returned by the [searchRoles](/sdk/php/3/core-classes/security/search-roles/) function.
:::

---

## deleteRole(id, [options], [callback])

| Arguments  | Type        | Description                               |
| ---------- | ----------- | ----------------------------------------- |
| `id`       | string      | Unique role identifier to delete          |
| `options`  | JSON Object | Optional parameters                       |
| `callback` | function    | (Optional) Callback handling the response |

---

## Options

| Option     | Type    | Description                                                                                                                  | Default     |
| ---------- | ------- | ---------------------------------------------------------------------------------------------------------------------------- | ----------- |
| `queuable` | boolean | Make this request queuable or not                                                                                            | `true`      |
| `refresh`  | string  | If set to `wait_for`, Kuzzle will wait the persistence layer to finish indexing (available with Elasticsearch 5.x and above) | `undefined` |

---

## Return Value

Returns the `Security` object to allow chaining.

---

## Callback Response

Returns the id of the rold that has been deleted.

## Usage

<<< ./snippets/delete-role-1.php

> Callback response

```json
"deleted role identifier"
```

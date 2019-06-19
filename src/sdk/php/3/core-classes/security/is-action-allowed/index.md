---
code: true
type: page
title: isActionAllowed
description: Security:isActionAllowed
---

# isActionAllowed

Specifies if an action is allowed, denied or conditional based on the rights provided as the first argument:

- `allowed` is returned when an action is authorized without condition
- `conditional` is returned when the authorization depends on a closure
- `denied` is returned when the action is forbidden

An action is defined as a pair of action and controller (mandatory), plus an index and a collection(optional).

:::info
You can get the rights from Kuzzle by using [Security.getUserRights](/sdk/php/3/core-classes/security/get-user-rights/) and [Kuzzle.getMyRights](/sdk/php/3/core-classes/kuzzle/get-my-rights/).
:::

---

## isActionAllowed(rights, controller, action, index, collection)

| Arguments    | Type       | Description    |
| ------------ | ---------- | -------------- |
| `rights`     | JSON array | Rights list    |
| `controller` | String     | The controller |
| `action`     | String     | The action     |
| `index`      | String     | The index      |
| `collection` | String     | The collection |

---

## Return Value

Returns either `allowed`, `denied` or `conditional`.

## Usage

<<< ./snippets/is-action-allowed-1.php

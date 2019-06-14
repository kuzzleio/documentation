---
code: true
type: page
title: setPolicies
description: Profile:setPolicies
---

# setPolicies

Replaces the roles associated with this security profile.

---

## `setPolicies(Array<String> policyIDs)`

| Arguments   | Type             | Description       |
| ----------- | ---------------- | ----------------- |
| `policyIDs` | array of strings | Policy IDs to add |

---

## `setPolicies(Array<JSONObject> policyDefinitions)`

| Arguments           | Type                  | Description               |
| ------------------- | --------------------- | ------------------------- |
| `policyDefinitions` | array of JSON objects | Policy definitions to add |

---

## Return Value

Returns the `Profile` object.

:::info
Updating a profile will have no impact until the `save` method is called.
:::

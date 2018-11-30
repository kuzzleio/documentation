---
layout: sdk.html.hbs
title: getPolicies
description: Profile:getPolicies
---
  

# getPolicies
Returns roles associated to this security policy.

---

## Return Value

Returns an array of roles linked to this security policy.

## Usage

[snippet=get-policies-1]
> Callback response

```json
[
  {
    "roleId": "<role name1>",
    "restrictedTo": {
      "index": "<some index>",
      "collections": ["<collection1>", "<collection2>", "<...>"]
    }
  },
  {
    "roleId": "<role name2>"
  },
  {
    "roleId": "<role name3>",
    "restrictedTo": {
      "index": "<some other index>",
      "collections": ["<collection>"]
    }
  }
]
```
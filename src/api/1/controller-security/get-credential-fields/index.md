---
layout: full.html.hbs
algolia: true
title: getCredentialFields
---



# getCredentialFields

{{{since "1.0.0"}}}

Retrieves the list of accepted field names by the specified authenticaiton strategy.


## Arguments

* `strategy`: authentication strategy

---

## Response

Returns an array of accepted field names.

### Example with the "local" authentication strategy:

```javascript
{
  "status": 200,                     
  "error": null,                     
  "action": "getCredentialFields",
  "controller": "security",
  "result": ["username", "password"]
}
```

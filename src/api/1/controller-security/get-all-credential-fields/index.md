---
layout: full.html.hbs
algolia: true
title: getAllCredentialFields
---



# getAllCredentialFields

{{{since "1.0.0"}}}

Retrieves the list of fields accepted by authentication strategies.


## Response

Returns an object with a property per authentication strategy, pointing to an array of accepted field names.

```javascript
{
  "status": 200,                     
  "error": null,                     
  "action": "getAllCredentialFields",
  "controller": "security",
  "result": {
    "local": ["username", "password"],
    "facebook": []
  }
}
```


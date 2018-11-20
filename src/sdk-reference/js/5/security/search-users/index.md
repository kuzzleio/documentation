---
layout: sdk.html.hbs
algolia: true
title: searchUsers
description: Security:searchUsers
---

  

# searchUsers
Return users matching the given filter.  


## Options

| Option | Type | Description | Default |
|
## Callback Response

Return a JSON Object

## Usage

[snippet=search-users-1]
> Callback response:

```json
{
  "total": 124,
  "users": [
    // array of User objects
  ],
  // only if a scroll parameter has been provided
  "scrollId": "<scroll identifier>"
}
```
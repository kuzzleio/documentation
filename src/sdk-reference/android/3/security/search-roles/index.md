---
layout: sdk.html.hbs
algolia: true
title: searchRoles
description: Security:searchRoles
---

  

# searchRoles
Search for security roles, optionally returning only the roles giving access to the provided controller names.


## Filters

| Filter | Type | Description | Default |
|
## Options

| Option | Type | Description | Default |
|
## Callback Response

Return a JSON Object

## Usage

[snippet=search-roles-1]
> Callback response:

```json
{
  "total": 124,
  "roles": [
    // array of Role
  ]
}
```
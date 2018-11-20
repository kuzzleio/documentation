---
layout: sdk.html.hbs
algolia: true
title: searchProfiles
description: Security:searchProfiles
---

  

# searchProfiles
Search for security profiles, optionally returning only those linked to the provided list of security roles.


## Options

| Option | Type | Description | Default |
|
## Filters

| Filter | Type | Description | Default |
|
## Callback Response

Returns a JSON Object 

## Usage

[snippet=search-profiles-1]
> Callback response:

```json
{
  "total": 124,
  "profiles": [
    // array of Profile objects
  ],
  // only if a scroll parameter has been provided
  "scrollId": "<scroll identifier>"
}
```
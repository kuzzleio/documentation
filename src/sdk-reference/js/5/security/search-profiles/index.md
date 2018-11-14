---
layout: sdk.html.hbs
algolia: true
title: searchProfiles
description: Security:searchProfiles
---
  

# searchProfiles

[snippet=search-profiles-1]
> Callback response:

[snippet=search-profiles-2]

Search for security profiles, optionally returning only those linked to the provided list of security roles.

---

## searchProfiles(filters, [options], callback)

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| ``filters`` | JSON Object | Search query |
| ``options`` | JSON Object | Optional parameters |
| ``callback`` | function | Callback handling the response |

---

## Options

| Option | Type | Description | Default |
|---------------|---------|----------------------------------------|---------|
| ``from`` | number | Starting offset | ``0`` |
| ``queuable`` | boolean | Make this request queuable or not  | ``true`` |
| ``scroll`` | string | Start a scroll session, with a time to live equals to this parameter's value following the [Elastisearch time format](https://www.elastic.co/guide/en/elasticsearch/reference/5.0/common-options.html#time-units) | ``undefined`` |
| ``size`` | integer | Number of hits to return per page | ``10`` |

---

## Filters

| Filter | Type | Description | Default |
|---------------|---------|----------------------------------------|---------|
| ``roles`` | array | Contains an array `roles` with a list of role id | ``[]`` |

---

## Callback Response

Returns a JSON Object 

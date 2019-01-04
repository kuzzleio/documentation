---
layout: sdk.html.hbs
title: getProfiles
description: User:getProfiles
---
  

# getProfiles
Gets the security [Profile]({{ site_base_path }}sdk-reference/php/3/profile) instances linked to the user from Kuzzle's API.

---

## getProfiles([options], callback)

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| ``options`` | JSON Object | Optional parameters |
| ``callback`` | function | Callback handling the response |

---

## Options

| Option | Type | Description | Default |
|---------------|---------|----------------------------------------|---------|
| ``queuable`` | boolean | Make this request queuable or not  | ``true`` |

---
## Callback Response

Returns an array of security [Profile]({{ site_base_path }}sdk-reference/php/3/profile) objects.

## Usage

[snippet=get-profiles-1]

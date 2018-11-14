---
layout: sdk.html.hbs
algolia: true
title: scrollProfiles
description: Security:scrollProfiles
---
  

# scrollProfiles
[snippet=scroll-profiles-1]
Scrolls on stored profiles using the provided scroll ID.

---

## scrollProfiles(scrollId, [options], callback)

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| ``scrollId`` | string | Scroll identifier retrieved from a search query |
| ``options`` | JSON Object | Optional parameters |
| ``callback`` | function | Callback handling the response |

---

## Options

| Option | Type | Description | Default |
|---------------|---------|----------------------------------------|---------|
| ``queuable`` | boolean | Make this request queuable or not  | ``true`` |

---

## Callback Response

Returns the list of fetched security profiles according to the scroll parameters (offset, limit etc.).

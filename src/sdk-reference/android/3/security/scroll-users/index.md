---
layout: sdk.html.hbs
algolia: true
title: scrollUsers
description: Security:scrollUsers
---
  

# scrollUsers
[snippet=scroll-users-1]
Scrolls on stored users using the provided scroll ID.

---

## scrollUsers(scrollId, [options], callback)

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

Returns the list of fetched users according to the scroll parameters (offset, limit etc.).

---
layout: sdk.html.hbs
algolia: true
title: scrollSpecifications
description: Collection:scrollSpecifications
---
  

# scrollSpecifications

[snippet=scroll-specifications-1]
> Callback response

[snippet=scroll-specifications-2]

Returns a JSON object containing the next page of the scroll session, and the `scrollId` to be used in the next `scroll` action.  
A scroll session is always initiated by a `searchSpecification` action with the `scroll` argument.

---

## scrollSpecifications(scrollId, [options], callback)

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| ``scrollId`` | string | The "scrollId" provided with the last scrollSpecifications response or from the initial searchSpecifications request |
| ``options`` | JSON object | Optional parameters |
| ``callback`` | function | Callback handling the response |

---

## Options

| Option | Type | Description | Default |
|---------------|---------|----------------------------------------|---------|
| ``queuable`` | boolean | Make this request queuable or not  | ``true`` |
| ``scroll`` | string | Re-initializes the scroll session timeout to its value. If not defined, the scroll timeout is defaulted to a Kuzzle configuration | ``undefined`` |

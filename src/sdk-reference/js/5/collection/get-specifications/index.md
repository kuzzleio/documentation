---
layout: sdk.html.hbs
algolia: true
title: getSpecifications
description: Collection:getSpecifications
algolia: true
---
  

# getSpecifications
Retrieves the specifications linked to the collection object.

---

## getSpecifications([options], callback)

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| ``options`` | JSON Object | Optional parameters |
| ``callback`` | function | Callback handling the response |

---

## Options

| Option | Type | Description | Default |
|---------------|---------|----------------------------------------|---------|
| ``queuable`` | boolean | Make this request queuable or not  | ``true`` |

## Usage

[snippet=get-specifications-1]
> Callback response

```json
{
  "validation": {
    "strict": "true",
      "fields": {
        "foo": {
          "mandatory": "true",
          "type": "string",
          "defaultValue": "bar"
        }
      }
    },
  "index": "index",
  "collection": "collection"
}
```
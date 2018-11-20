---
layout: sdk.html.hbs
algolia: true
title: getSpecifications
description: Collection:getSpecifications
---

  

# getSpecifications
Retrieves the specifications linked to the collection object.


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
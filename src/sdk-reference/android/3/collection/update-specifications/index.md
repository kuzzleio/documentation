---
layout: sdk.html.hbs
algolia: true
title: updateSpecifications
description: Collection:updateSpecifications
---

  

# updateSpecifications
Update parts of a specification, by replacing some fields or adding new ones.  
Note that you cannot remove fields this way: missing fields will simply be left unchanged.


## Options

| Option | Type | Description | Default |
|
## Return Value

Returns the `Collection` object to allow chaining.

## Usage

[snippet=update-specifications-1]
> Callback response

```json
{
  "index": {
    "collection": {
      "strict":"true",
      "fields": {
        "foo": {
          "mandatory": "true",
          "type": "string",
          "defaultValue": "bar"
        }
      }
    }
  }
}
```
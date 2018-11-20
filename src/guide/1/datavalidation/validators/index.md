---
layout: full.html.hbs
algolia: true
title: Validators
description: learn how to create complex validators
order: 200
---


# The `validators` property

The `validators` property is an array of [Koncorde filters]({{ site_base_path }}kuzzle-dsl/#FIXME). Each filter has to match in order for the document to be valid.

<aside class="warning">You have to be careful with fields that are empty or undefined.</aside>

## Structure

```json
{
  "myIndex": {
    "myCollection": {
      "strict": true,
      "fields": {
        "..."
      },
      "validators": [
        { "equals": { "fieldName": "maximilian"} },
        {
          "bool": {
            "must": [
              "..."
            ],
            "must_not": [
              "..."
            ],
            "should": [
              "..."
            ],
            "should_not": [
              "..."
            ]
          }
        },
        "..."
      ]
    },
    "..."
  },
  "..."
}
```

Translates to the following Koncorde query:

```json
{
  "bool": {
    "must": [
      { "equals": { "fieldName": "maximilian"} },
      {
        "bool": {
          "must": [
            "..."
          ],
          "must_not": [
            "..."
          ],
          "should": [
            "..."
          ],
          "should_not": [
            "..."
          ]
        }
      },
      "..."
    ]
  }
}
```

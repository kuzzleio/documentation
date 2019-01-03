---
layout: full.html.hbs
title: Validators
description: learn how to create complex validators
order: 200
---

# The `validators` property

The `validators` property is an array of [Koncorde filters]({{ site_base_path }}koncorde/1/#FIXME). Each filter has to match in order for the document to be valid.

<div class="alert alert-warning">You have to be careful with fields that are empty or undefined.</div>

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

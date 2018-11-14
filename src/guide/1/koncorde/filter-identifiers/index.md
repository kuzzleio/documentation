---
layout: full.html.hbs
title: Filter identifiers
order: 100
description: How Koncorde generates filter identifiers
---

## Filter Identifiers

Koncorde filter identifiers are generated from the filters provided in the DSL
after they are represented in their [disjunctive normal form](https://en.wikipedia.org/wiki/Disjunctive_normal_form),
which guarantees that different **filters that match the same scope will have the same identifier**.

For example, both these filters will have the same filter identifier:

```json
{
  "and": [
    {
      "not": {
        "in": {"some_document_field": ["foo", "bar"]}
      }
    },
    {"missing": {"field": "another_field"}}
  ]
}
```

And:

```json
{
  "not": {
    "or": [
      {
        "or": [
          {"equals": {"some_document_field": "foo"}},
          {"equals": {"some_document_field": "bar"}}
        ]
      },
      {"exists": {"field": "another_field"}}
    ]
  }
}
```

For more information, please refer to the [Koncorde](https://www.npmjs.com/package/koncorde#filter-unique-identifier) documentation.

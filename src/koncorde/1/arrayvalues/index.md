---
layout: full.html.hbs
algolia: true
title: Matching Array VSalues
description: How to test array values
order: 300
---

## Matching array values

A few keywords, like [exists]({{ site_base_path }}kuzzle-dsl/terms/exists) or [missing]({{ site_base_path }}kuzzle-dsl/terms/missing), allow searching for array values.

These values can be accessed with the following syntax: `<array path>[<value>]`  
Only one array value per `exists`/`missing` keyword can be searched in this manner.

Array values must be scalar. Allowed types are `string`, `number`, `boolean` and the `null` value.

The array value must be provided using the JSON format:

* Strings: the value must be enclosed in double quotes. Example: `foo["string value"]`
* Numbers, booleans and `null` must be used as is. Examples: `foo[3.14]`, `foo[false]`, `foo[null]`


Array values can be combined with [nested properties]({{ site_base_path }}kuzzle-dsl/essential/nested): `nested.array["value"]`

### Example

Given the following document:

```json
{
    "name": {
        "first": "Grace",
        "last": "Hopper",
        "hobbies": ["compiler", "COBOL"]
    }
}
```

Here is a filter, testing whether the value `compiler` is listed in the array `hobbies`:

```javascript
{
    "exists": 'name.hobbies["compiler"]'
}
```

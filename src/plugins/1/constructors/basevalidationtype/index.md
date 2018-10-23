---
layout: full.html.hbs
algolia: true
title: BaseValidationType
---

# BaseValidationType

{{{since "1.0.0"}}}

Abstract class, used to create custom validation types (see the [validation]({{ site_base_path }}plugins/1/accessors/validation) accessor).

---

## Constructor

This class constructor takes no argument.

---

## Properties

* `allowChildren`: {boolean} if `false`, the field must be a scalar
* `allowedTypeOptions`: {string[]} the list of allowed data type options
* `typeName`: {string} data type name

---

## validate (abstract)

Validates a field against this implemented data type.

This is an abstract method. If not overloaded, it always returns `true`

### Arguments

`validate(opts, field, errors)`

* `opts`: {object} data type options. The provided object can only contain the keys defined in the `allowedTypeOptions` property
* `field`: data to validate
* `errors`: {string[]} if the provided `field` is not valid, the reason must be pushed in that array

### Return

The `validate` function returns a boolean telling whether the field is valid.

---

## validateFieldSpecification (abstract)

Validates a new configuration for this data type.

This is an abstract method. If not overloaded, it always returns `true`

### Arguments

`validateFieldSpecification(opts)`

* `opts`: {object} data type options. The provided object can only contain the keys defined in the `allowedTypeOptions` property

### Return

The `validateFieldSpecification` returns a copy of the `opts` object, updated with interpreted values.

If the provided options are not valid, this function is expected to throw a [KuzzleError]({{ site_base_path }}plugins/1/errors) error.

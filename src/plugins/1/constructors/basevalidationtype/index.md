---
layout: full.html.hbs
algolia: true
title: BaseValidationType
---


# BaseValidationType

{{{since "1.0.0"}}}

Abstract class, used to create custom validation types (see the [validation]({{ site_base_path }}plugins/1/accessors/validation) accessor).


## Properties

| Property | Type | Description |
|
## validate (abstract)

Validates a field against this implemented data type.

This is an abstract method. If not overloaded, it always returns `true`

### Arguments

```js
validate(opts, field, errors)
```

<br/>

| Arguments | Type | Description |
|
## validateFieldSpecification (abstract)

Validates a new configuration for this data type.

This is an abstract method. If not overloaded, it always returns `true`

### Arguments

```js
validateFieldSpecification(opts)
```

<br/>

| Arguments | Type | Description |
|-----------|------|-------------|
| `opts` | <pre>object</pre> | Data type options. The provided object can only contain the keys defined in the `allowedTypeOptions` property |

### Return

The `validateFieldSpecification` returns a copy of the `opts` object, updated with interpreted values.

If the provided options are not valid, this function is expected to throw a [KuzzleError]({{ site_base_path }}plugins/1/errors) error.

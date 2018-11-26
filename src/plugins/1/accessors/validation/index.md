---
layout: full.html.hbs
algolia: true
title: validation
algolia: true
---

# validation

Accessor to the [data validation API]({{ site_base_path }}validation-reference/1/)

---

## addType

{{{since "1.0.0"}}}

Adds a new data type, to be used for document validation.

### Arguments

```js
addType(Type)
```

<br/>

| Arguments | Type | Description |
|-----------|------|-------------|
| `Type` | <pre>object</pre> | A class implementing the abstract [BaseValidationType]({{ site_base_path }}plugins/1/constructors/basevalidationtype) class |

---

## validate

{{{since "1.0.0"}}}

Validates the content of a request body (mutates the request).

### Arguments

```js
validate(request, [verbose])
```
<br/>

| Arguments | Type | Description |
|-----------|------|-------------|
| `request` | <a href={{ site_base_path }}plugins/1/constructors/request><pre>Request</pre></a> | Request object with a non-empty body content |
| `verbose` | <pre>boolean</pre> | If true, returns an exhaustive validation report, instead of failing at the first error encountered |

### Return

The `validate` function returns a promise, and if relevant, default values are applied to the provided request.

If a validation error occurs, the behavior depends on the `validation` optional parameter:

* `false` (default): the promise is rejected with the first error encountered
* `true`: the promise is resolved even if the validation fails. The promise resolves a validation status report, containing the following properties:
  * `validation`: {boolean} validation state
  * `errorMessages`: {array} the exhaustive list of encountered errors

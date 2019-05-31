---
code: false
type: page
title: Introduction
order: 0
description: The reference for the data-validation engine in Kuzzle.
---

# Data Validation

Kuzzle natively provides a way to validate documents that you create, replace, update or publish.
You can specify the validation specification in the Kuzzle configuration file under `validation`.

Validation specifications are always attached to the collection of an index.

The API offers several actions to perform on validation specifications, allowing you to read, update, delete or validate them. Please refer to the [API documentation](/core/1/api/api-reference/controller-collection/update-specifications/) for more information.

A validation specification is composed of three properties:

- [`fields`](/core/1/guide/cookbooks/datavalidation/fields/): this property is intended to describe the document's fields. It includes the definition of their type, boundaries, if they're required or not etc. depending on the type of the field.
- [`validators`](/core/1/guide/cookbooks/datavalidation/validators/): this property is intended to match the document using [Koncorde filters](/core/1/koncorde/#FIXME). It can be used to build conditionnal filters, acceptance criterias, etc.
- [`strict`](/core/1/guide/cookbooks/datavalidation/fields/#the-strict-property-default): this property determines if the field specification is strict. If it is, the addition of unknown fields (i.e. not defined in the `fields` property) is forbidden and will be rejected.

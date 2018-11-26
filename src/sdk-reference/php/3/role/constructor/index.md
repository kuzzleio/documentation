---
layout: sdk.html.hbs
algolia: true
title: constructor
description: Role:constructor
order: 1
algolia: true
---
  

# Constructors
Instantiates a new `Role` object, which defines a set of right policies.

---

## Role(Security, id, content, [meta])

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| ``Security`` | Security | An instantiated [Security]({{ site_base_path }}sdk-reference/security) object |
| ``id`` | string | Unique role identifier |
| ``content`` | JSON Object | Role content |
| ``meta`` | JSON Object | Role metadata |

**Note:**  this constructor won't make any call to Kuzzle.

---

## Properties

| Property name | Type | Description | get/set |
|--------------|--------|-----------------------------------|---------|
| `content` | JSON object | Raw role content | get |
| `id` | string | Unique profile identifier | get |
| `meta` | JSON object | Role metadata | get |

---

## Return Value

Returns the `Role` object.

## Usage

[snippet=constructor-1]

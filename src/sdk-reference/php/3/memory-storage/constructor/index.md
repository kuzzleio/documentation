---
layout: sdk.html.hbs
title: constructor
description: MemoryStorage:constructor
order: 1
---
  

# Constructor
Kuzzle's memory storage is a data store separate from the database layer.
It uses Redis internally, and most of its underlying functions are exposed by Kuzzle.

---

## MemoryStorage(Kuzzle)

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| `Kuzzle` | object | An instantiated [Kuzzle]({{ site_base_path }}sdk-reference/php/3/kuzzle) SDK object |

## Usage

[snippet=constructor-1]

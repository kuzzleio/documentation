---
layout: sdk.html.hbs
algolia: true
title: connect
description: Kuzzle:connect
---
  

# connect
Connects to Kuzzle using the `host` parameter provided in the constructor.
Has no effect if ``connect`` is set to ``auto``, unless ``disconnect`` has been called first.

---

## Return value

Returns the `Kuzzle` object to allow chaining.

---

## Callback Response

If a callback has been provided to the `Kuzzle` constructor, it will be called with the `Kuzzle` instance once connected to Kuzzle

## Usage

[snippet=connect-1]

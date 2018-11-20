---
layout: sdk.html.hbs
algolia: true
title: keys
description: MemoryStorage:keys
---

  

# keys
Returns all keys matching the provided pattern.

[[_Redis documentation_]](https://redis.io/commands/keys)


## Options

| Option | Type | Description | Default |
|
## Callback Response

Returns an array of key names matching the provided pattern.

## Usage

[snippet=keys-1]
> Callback response:

```json
[
  "foo",
  "foobar",
  "foofighters"
]
```
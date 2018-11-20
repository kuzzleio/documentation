---
layout: sdk.html.hbs
algolia: true
title: exists
description: MemoryStorage:exists
---

  

# exists
Checks if the specified keys exist in the database.

[[_Redis documentation_]](https://redis.io/commands/exists)


## Options

| Option | Type | Description | Default |
|
## Callback Response

Returns an integer containing the number of existing keys amongst the provided list.

## Usage

[snippet=exists-1]
> Callback response:

```json
2
```
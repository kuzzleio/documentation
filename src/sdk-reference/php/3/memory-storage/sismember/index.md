---
layout: sdk.html.hbs
algolia: true
title: sismember
description: MemoryStorage:sismember
---

  

# sismember
Checks if `member` is a member of the set of unique values stored at `key`.

[[_Redis documentation_]](https://redis.io/commands/sismember)


## Options

| Option | Type | Description | Default |
|
## Callback Response

Returns a boolean specifying if `member` is a member of the set or not.
## Usage

[snippet=sismember-1]
> Callback response:

```json
true
```
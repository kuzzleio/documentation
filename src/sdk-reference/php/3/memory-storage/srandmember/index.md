---
layout: sdk.html.hbs
algolia: true
title: srandmember
description: MemoryStorage:srandmember
---

  

# srandmember
Returns one or more members of a set of unique values, at random.  
If `count` is provided and is positive, the returned values are unique. If `count` is negative, a set member can be returned multiple times.

[[_Redis documentation_]](https://redis.io/commands/srandmember)


## Options

| Option | Type | Description | Default |
|
## Callback Response

Returns an array of members of a set of unique values.

## Usage

[snippet=srandmember-1]
> Callback response:

```json
[
  "member1",
  "member2",
  "..."
]
```
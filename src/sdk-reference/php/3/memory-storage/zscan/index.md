---
layout: sdk.html.hbs
algolia: true
title: zscan
description: MemoryStorage:zscan
---

  

# zscan
Identical to [scan]({{ site_base_path }}sdk-reference/memory-storage/scan), except that `zscan` iterates the members held by a sorted set.

[[_Redis documentation_]](https://redis.io/commands/zscan)


## Options

| Option | Type | Description | Default |
|
## Callback Response

Returns a JSON array containing 2 entries:

* the cursor position for the next page of results (a next position of `0` indicates the end of the scan)
* an array of sorted set members and their associated scores

## Usage

[snippet=zscan-1]
> Callback response:

```json
{
  "cursor": 18,
  "values": [
    "member1",
    "member1's score",
    "member2",
    "member2's score",
    "..."
  ]
}
```
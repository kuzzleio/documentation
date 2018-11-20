---
layout: sdk.html.hbs
algolia: true
title: sscan
description: MemoryStorage:sscan
---

  

# sscan
Identical to [scan]({{ site_base_path }}sdk-reference/memory-storage/scan), except that `sscan` iterates the members held by a set of unique values.

[[_Redis documentation_]](https://redis.io/commands/sscan)


## Options

| Option | Type | Description | Default |
|
## Callback Response

Returns a JSON array containing 2 entries:

* the cursor position for the next page of results (a next position of `0` indicates the end of the scan)
* an array of members

## Usage

[snippet=sscan-1]
> Callback response:

```json
{
  "cursor": 18,
  "values": [
    "member1",
    "member2",
    "..."
  ]
}
```
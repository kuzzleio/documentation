---
layout: sdk.html.hbs
algolia: true
title: hscan
description: MemoryStorage:hscan
---

  

# hscan
Identical to [scan]({{ site_base_path }}sdk-reference/memory-storage/scan), except that `hscan` iterates over the fields contained in a hash.  

[[_Redis documentation_]](https://redis.io/commands/hscan)


## Options

| Option | Type | Description | Default |
|
## Callback Response

Returns an object containing 2 entries:

* the cursor position for the next page of results (a next position of `0` indicates the end of the scan)
* an array of field names and values

## Usage

[snippet=hscan-1]
> Callback response:

```json
{
  "cursor": 18,
  "values": [
    "field1",
    "field1 value",
    "field2",
    "field2 value"
  ]
}
```
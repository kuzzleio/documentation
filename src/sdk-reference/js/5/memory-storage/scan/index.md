---
layout: sdk.html.hbs
algolia: true
title: scan
description: MemoryStorage:scan
---

  

# scan
Iterates incrementally over the set of keys in the database using a cursor.

An iteration starts when the cursor is set to `0`.  
To get the next page of results, simply re-send the identical request with the updated cursor position provided in the result set.  
The scan terminates when the next position cursor returned by the server is `0`.

[[_Redis documentation_]](https://redis.io/commands/scan)


## Options

| Option | Type | Description | Default |
|
## Callback Response

Returns a JSON object containing 2 entries:

* the cursor position for the next page of results (a next position of `0` indicates the end of the scan)
* a list of fetched keys

## Usage

[snippet=scan-1]
> Callback response:

```json
{
  "cursor": 18,
  "values": [
    "key1",
    "key2",
    "..."
  ]
}
```
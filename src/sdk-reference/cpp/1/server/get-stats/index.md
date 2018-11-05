---
layout: sdk.html.hbs
algolia: true
title: getStats
description: Returns statistics snapshots within a provided timestamp range.
order: 200
---

# getStats

{{{since "1.0.0"}}}

Returns statistics snapshots within a provided timestamp range.
By default, snapshots are made every 10 seconds and they are stored for 1 hour.

These statistics include:

* the number of connected users per protocol (not available for all protocols)
* the number of ongoing requests
* the number of completed requests since the last frame
* the number of failed requests since the last frame

## Signature

```cpp
std::string getStats(time_t start, time_t end, query_options* options=nullptr)
```

## Arguments

| Arguments | Type          | Description                                             | Required |
| --------- | ------------- | ------------------------------------------------------- | -------- |
| `startTime` | time_t                   | begining of statistics frame set (timestamp or datetime format) | yes       |
| `stopTime`  | time_t                   | end of statistics frame set (timestamp or datetime format)      | yes       |
| `options`   | kuzzleio::query_options* | A pointer to a `query_options` containing query options           |  no       |

### **Options**

Additional query options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Return
Returns statistics snapshots within a provided timestamp range as `std::string`.

## Usage

[snippet=get-stats]

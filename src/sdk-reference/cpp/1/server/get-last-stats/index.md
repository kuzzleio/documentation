---
layout: sdk.html.hbs
algolia: true
title: GetLastStats
description: Returns the most recent statistics snapshot.
order: 200
---

# GetLastStats

{{{since "1.0.0"}}}

Returns the most recent statistics snapshot.
By default, snapshots are made every 10 seconds and they are stored for 1 hour.

These statistics include:

* the number of connected users per protocol (not available for all protocols)
* the number of ongoing requests
* the number of completed requests since the last frame
* the number of failed requests since the last frame

## Signature

```cpp
std::string getLastStats(query_options* options=nullptr)
```

## Arguments

| Arguments | Type          | Description                                             | Required |
| --------- | ------------- | ------------------------------------------------------- | -------- |
| `options` | kuzzleio::query_options* | A pointer to a `query_options` containing query options | no       |

### **Options**

Additional query options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |


## Return
Returns the most recent statistics snapshot as `std::string`.

## Usage

[snippet=get-last-stats]

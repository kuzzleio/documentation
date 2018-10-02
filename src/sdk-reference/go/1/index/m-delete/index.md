---
layout: sdk.html.hbs
algolia: true
title: mDelete
description: Deletes multiple indexes
order: 600
---

# mDelete

Deletes multiple indexes at once.

## Signature

```go
MDelete(indexes []string, options types.QueryOptions) ([]string, error)
```

## Arguments

| Arguments | Type         | Description                                   | Required |
| --------- | ------------ | --------------------------------------------- | -------- |
| `indexes` | Array        | An array of strings containing indexes names. | yes      |
| `options` | QueryOptions | Query options.         | no       |

### **Options**

Additional query options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | bool | Make this request queuable or not | `true`  |

## Return

Returns an `Array` of strings containing the list of indexes names deleted or an error

## Usage

[snippet=mDelete]

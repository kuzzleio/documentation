---
layout: sdk.html.hbs
algolia: true
title: create
description: Create an index
order: 200
---

# create

Create a new index in Kuzzle

## Signature

```go
Create(index string, options types.QueryOptions) error
```

## Arguments

| Arguments | Type         | Description                           | Required |
| --------- | ------------ | ------------------------------------- | -------- |
| `index`   | string       | Index name                            | yes      |
| `options` | QueryOptions | Query options. | no       |

### **Options**

Additional query options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | bool | Make this request queuable or not | `true`  |

## Return

Return an error or `nil` if index successfully created.

## Usage

[snippet=create]

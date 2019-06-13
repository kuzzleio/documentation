---
code: true
type: page
title: getMapping
description: Return collection mapping
---

# getMapping

Returns the mapping for the given `collection`.

## Signature

```go
GetMapping(index string, collection string, options types.QueryOptions) (json.RawMessage, error)
```

## Arguments

| Arguments    | Type               | Description     | Required |
| ------------ | ------------------ | --------------- | -------- |
| `index`      | string             | Index name      | yes      |
| `collection` | string             | Collection name | yes      |
| `options`    | types.QueryOptions | Query options   | no       |

### **options**

Additional query options

| Property   | Type | Description                       | Default |
| ---------- | ---- | --------------------------------- | ------- |
| `queuable` | bool | Make this request queuable or not | `true`  |

## Return

Return a json representation of the mapping and an error is something was wrong.

## Usage

<<< ./snippets/get-mapping.go

## Signature

```go
Delete(index string, options types.QueryOptions) error
```

## Usage

[code-example=delete]

## Arguments

### index

A `string` representing the index name.

### options

Query options.

| Option   | Type    | Description                       | Default |
| -------- | ------- | --------------------------------- | ------- |
| queuable | boolean | Make this request queuable or not | true    |

---

## Return

Returns an error or nil if the request succeed.

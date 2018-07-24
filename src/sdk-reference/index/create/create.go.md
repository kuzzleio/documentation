## Signature

``` go
Create(index string, options types.QueryOptions) error
```

## Usage

[code-example=create]

## Arguments

### index

A `string` representing the index name.

### options

A structure `queryOptions` containing query options.

[code-example=options]

| Option   | Type    | Description                       | Default |
| -------- | ------- | --------------------------------- | ------- |
| queuable | boolean | Make this request queuable or not | true    |

---

## Return

Return an error or `nil` if index successfully created.
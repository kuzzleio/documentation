## Signature

```go
MDelete(indexes []string, options types.QueryOptions) ([]string, error)
```

## Arguments

| Arguments     | Type        | Description                            | Required
|---------------|-------------|----------------------------------------|----------
| ``indexes``   | Array       | An array of strings containing indexes names. | yes
| ``options``   | QueryOptions | A structure containing query options. | no

### __Options__

Additional query options

| Option   | Type    | Description                       | Default |
| -------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`    |

## Return

Returns an `Array` of strings containing the list of indexes names deleted or an error

## Usage

[code-example=mDelete]
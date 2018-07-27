## Signature

```go
Delete(index string, options types.QueryOptions) error
```

## Arguments

| Arguments     | Type        | Description                            | Required
|---------------|-------------|----------------------------------------|----------
| ``index``     | String      | Index name               | yes
| ``options``   | QueryOptions | A structure containing query options. | no


### __Options__

Additional query options

| Option   | Type    | Description                       | Default |
| -------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`    |

## Return

Returns an error or `nil` if the request succeed.

## Usage

[code-example=delete]
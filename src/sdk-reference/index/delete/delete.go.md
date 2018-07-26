## Signature

```go
Delete(index string, options types.QueryOptions) error
```

## Arguments

| Arguments     | Type        | Description |
|---------------|-------------|----------------------------------------|
| ``index``     | String      | Represent the index name |
| ``options``   | QueryOptions | A structure containing query options. |

### __Options__

Query options details :

| Option   | Type    | Description                       | Default |
| -------- | ------- | --------------------------------- | ------- |
| queuable | boolean | Make this request queuable or not | true    |

## Return

Returns an error or nil if the request succeed.

## Usage

[code-example=delete]
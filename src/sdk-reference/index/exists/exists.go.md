## Signature

```go
Exists(index string, options types.QueryOptions) (bool, error)
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

Returns a boolean that indicate whether the index exists, or an error

## Usage

[code-example=exists]
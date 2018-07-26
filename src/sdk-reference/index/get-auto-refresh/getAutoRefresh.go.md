## Signature

```go
GetAutoRefresh(index string, options types.QueryOptions) (bool, error)
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

Returns a boolean that indicate the status of the **autoRefresh** flag.

## Usage

[code-example=getAutoRefresh]
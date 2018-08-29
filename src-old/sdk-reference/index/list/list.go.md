## Signature

```go
List(options types.QueryOptions) ([]string, error)
```

## Arguments

| Arguments     | Type        | Description |
|---------------|-------------|----------------------------------------|
| ``options``   | QueryOptions | A structure containing query options. |

### __Options__

Additional query options

| Option   | Type    | Description                       | Default |
| -------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`    |

## Return

Returns an `Array` of strings containing the list of indexes names present in Kuzzle or an error

## Usage

[code-example=list]
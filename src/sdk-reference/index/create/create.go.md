## Signature

``` go
Create(index string, options types.QueryOptions) error
```

## Arguments

| Arguments     | Type        | Description                            | Required
|---------------|-------------|----------------------------------------|----------
| ``index``     | String      | Represent the index name               | yes
| ``options``   | QueryOptions | A structure containing query options. | no

### __Options__

Query options details :

| Option   | Type    | Description                       | Default |
| -------- | ------- | --------------------------------- | ------- |
| queuable | boolean | Make this request queuable or not | true    |

## Return

Return an error or `nil` if index successfully created.

## Usage

[code-example=create]
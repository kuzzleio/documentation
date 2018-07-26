## Signature

``` cpp
void delete_(std::string index, query_options *options = null)
```

## Arguments

| Arguments     | Type        | Description |
|---------------|-------------|----------------------------------------|
| ``index``     | std::string  | Represent the index name |
| ``options``   | query_options | A pointer to a `query_options` containing query options|

### __Options__

Query options details :

| Option   | Type    | Description                       | Default |
| -------- | ------- | --------------------------------- | ------- |
| queuable | boolean | Make this request queuable or not | true    |

## Return

Returns nothing.

## Exceptions

Throw a KuzzleException

## Usage

[code-example=delete]
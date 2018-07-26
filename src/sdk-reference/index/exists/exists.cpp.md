## Signature

``` cpp
bool exists(std::string index, query_options *options = null)
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

Returns a boolean that indicate whether the index exists or not.

## Exceptions

Throw a KuzzleException

## Usage

[code-example=exists]

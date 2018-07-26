## Signature

``` cpp
bool getAutoRefresh(std::string index, query_options *options)
```

## Arguments

| Arguments     | Type        | Description               | Required
|---------------|-------------|---------------------------|----------
| ``index``     | std::string  | Represent the index name | yes
| ``options``   | query_options | A pointer to a `query_options` containing query options| no

### __Options__

Query options details :

| Option   | Type    | Description                       | Default |
| -------- | ------- | --------------------------------- | ------- |
| queuable | boolean | Make this request queuable or not | true    |


## Return

Returns a boolean that indicate the status of the **autoRefresh** flag.

## Exceptions

Throw a KuzzleException

## Usage

[code-example=getAutoRefresh]

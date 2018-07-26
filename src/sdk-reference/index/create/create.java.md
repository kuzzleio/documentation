## Signature

``` java
void create(String index, QueryOptions options)
```

## Arguments

| Arguments     | Type        | Description |
|---------------|-------------|----------------------------------------|
| ``index``     | String      | Represent the index name |
| ``options``   | QueryOptions | The query options |

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

[code-example=create]
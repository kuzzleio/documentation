## Signature

``` java
void create(String index, QueryOptions options)
```

## Arguments

| Arguments     | Type        | Description              | Required
|---------------|-------------|--------------------------|-----------
| ``index``     | String      | Represent the index name | yes
| ``options``   | QueryOptions | The query options       | no

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
## Signature

```cpp
void create(std::string index, query_options *options = null)
```

## Arguments

| Arguments     | Type        | Description                            | Required
|---------------|-------------|----------------------------------------|----------
| ``index``     | std::string  | Index name              | yes
| ``options``   | query_options | A pointer to a `query_options` containing query options| no

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

## Signature

``` cpp
bool exists(std::string index, kuzzleio::query_options *options = null)
```

## Arguments

| Arguments     | Type        | Description                            | Required
|---------------|-------------|----------------------------------------|----------
| ``index``     | std::string  | Index name              | yes
| ``options``   | query_options | A pointer to a `query_options` containing query options| no

### __Options__

Additional query options

| Option   | Type    | Description                       | Default |
| -------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`    |


## Return

Returns a `boolean` that indicate whether the index exists or not.

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/essentials/error-handling).

## Usage

[code-example=exists]

``` java
void create(String index, QueryOptions options)
void create(String index)
```

## Arguments

| Arguments     | Type         | Description              | Required
|---------------|------------- |--------------------------|-----------
| ``index``     | String       | Index name               | yes
| ``options``   | QueryOptions | The query options       | no

### __Options__

Additional query options

| Option   | Type    | Description                       | Default |
| -------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`    |

## Exceptions

- one of the standard [Kuzzle exceptions]({{ site_base_path }}/sdk-reference/essentials/exceptions#standard)
- `PreconditionException` if Kuzzle was not able to process the request due to an invalid state (eg: non-existing collection)
- `SizeLimitException` if the request size exceeds the limits defined by the [Kuzzle configuration]({{ site_base_path }}/guide/essentials/configuration)

## Usage

[code-example=create]

## Signature

``` java
StringVector list(QueryOptions options)
StringVector list()
```

## Arguments

| Arguments     | Type        | Description |
|---------------|-------------|----------------------------------------|
| ``options``   | QueryOptions | The query options |

### __Options__

Additional query options

| Option   | Type    | Description                       | Default |
| -------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`    |

## Return

Returns an `StringVector` containing the list of indexes names present in Kuzzle (more details about [StringVector]({{ site_base_path }}sdk-reference/essentials/stringvector))

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/essentials/error-handling).

## Usage

[code-example=list]

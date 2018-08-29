## Signature

``` java
StringVector mDelete(StringVector indexes, QueryOptions options)
StringVector mDelete(StringVector indexes)
```

## Arguments

| Arguments     | Type         | Description              | Required
|---------------|------------- |--------------------------|-----------
| ``indexes``   | StringVector | List of indexes names     | yes
| ``options``   | QueryOptions | The query options       | no

### __Options__

Additional query options

| Option   | Type    | Description                       | Default |
| -------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`    |

## Return

Returns an `StringVector` containing the list of indexes names deleted (more details about [StringVector]({{ site_base_path }}sdk-reference/essentials/stringvector))

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/essentials/error-handling).

## Usage

[code-example=mDelete]

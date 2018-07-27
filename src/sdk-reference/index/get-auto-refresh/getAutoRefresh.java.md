## Signature

``` java
boolean getAutoRefresh(String index, QueryOptions options)
boolean getAutoRefresh(String index)
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

## Return

Returns a `boolean` that indicate the status of the **autoRefresh** flag.

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/essentials/error-handling).

## Usage

[code-example=getAutoRefresh]

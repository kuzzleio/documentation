## Signature

``` java
void setAutoRefresh(String index, boolean autoRefresh, QueryOptions options)
void setAutoRefresh(String index, boolean autoRefresh)
```

## Arguments

| Arguments     | Type         | Description              | Required
|---------------|------------- |--------------------------|-----------
| ``index``     | String       | Index name               | yes
| ``autoRefresh`` | Boolean    | autoRefresh flag     | yes
| ``options``   | QueryOptions | The query options       | no

### __Options__

Additional query options

| Option   | Type    | Description                       | Default |
| -------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`    |

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/essentials/error-handling).

## Usage

[code-example=setAutoRefresh]

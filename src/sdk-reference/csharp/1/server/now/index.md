# now

{{{since "1.0.0"}}}

Fetch the current server timestamp, in Epoch-millis format.

## Signature

```csharp
public long now();
public long now(QueryOptions options);
```

### **Options**

Additional query options

| Option     | Type   | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | bool | If true, queues the request during downtime, until connected to Kuzzle again | `true`  |

## Return

Returns Epoch-millis timestamp as `long long`.

## Return

Returns Epoch-millis timestamp as `long long`.

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/csharp/1/essentials/error-handling).


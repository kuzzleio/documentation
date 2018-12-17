# publish

Sends a real-time `<message>` to Kuzzle. The `<message>` will be dispatched to all clients with subscriptions matching the `<index>`, the `<collection>` and the `<message>` content.

The `<index>` and `<collection>` are indicative and serve only to distinguish the rooms. They are not required to exist in the database

**Note:** real-time messages are not persisted in the database.

## Signature

```csharp
public void publish(string index, string collection, string body);
public void publish(string index, string collection, string body, QueryOptions options);
```

### options

Additional query options

| Option     | Type<br/>(default)  | Description   |
| ---------- | ------- | --------------------------------- |
| `queuable` | <pre>bool</pre><br/>(`true`) | Make this request queuable or not |

## Exceptions

Throws a `Kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/csharp/1/essentials/error-handling).

## Usage

[snippet=publish]

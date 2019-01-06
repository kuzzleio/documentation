# publish

Sends a real-time message to Kuzzle. The message will be dispatched to all clients with subscriptions matching the index, the collection and the message content.

The index and collection are indicative and serve only to distinguish the rooms. They are not required to exist in the database

**Note:** real-time messages are not persisted in the database.

## Signature

```csharp
public void publish(string index, string collection, string message);

public void publish(
    string index, 
    string collection, 
    string message, 
    query_options options);

```

## Arguments

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| `index` | <pre>string</pre> | Index name    |
| `collection` | <pre>string</pre> | Collection name    |
| `message` | <pre>string</pre> | JSON string representing a JSON payload |
| `options` | <pre>Kuzzleio::QueryOptions\*</pre> | Query options |

### options

Additional query options

| Option     | Type<br/>(default)  | Description   |
| ---------- | ------- | --------------------------------- |
| `queuable` | <pre>bool</pre><br/>(`true`) |  If true, queues the request during downtime, until connected to Kuzzle again |
| `volatiles` | <pre>string</pre><br/>(`"{}"`) | JSON string representing subscription information, used in [user join/leave notifications]({{site_base_path}}api/1/essentials/volatile-data/) |

## Exceptions

Throws a `Kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/csharp/1/error-handling).

## Usage

[snippet=publish]

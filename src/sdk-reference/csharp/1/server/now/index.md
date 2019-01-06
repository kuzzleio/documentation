# now

Fetch the current server timestamp, in Epoch-millis format.

## Signature

```csharp
public long now();
public long now(QueryOptions options);
```

## Arguments

| Arguments | Type          | Description       |
| --------- | ------------- | ------------------|
| `options` | <pre>Kuzzleio::QueryOptions\*</pre> | Query options |

### options

Additional query options

| Option     | Type<br/>(default)   | Description  |
| ---------- | ------- | -------------- |
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Return

An Epoch-millis timestamp.

## Return

An Epoch-millis timestamp.

## Return

An Epoch-millis timestamp.


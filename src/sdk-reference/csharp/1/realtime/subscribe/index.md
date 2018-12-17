# subscribe

Subscribes by providing a set of `<filters>`: messages, document changes and, optionally, user events matching the provided `<filters>` will generate [real-time notifications]({{site_base_path}}api/1/notifications), sent to you in real-time by Kuzzle.

## Signature

```csharp
public string subscribe(string index, string collection, string body, SWIGTYPE_p_std__functionT_void_fkuzzleio__notification_result_pF_t cb);
public string subscribe(string index, string collection, string body, SWIGTYPE_p_std__functionT_void_fkuzzleio__notification_result_pF_t cb, room_options options);
```

## Arguments

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| `index` | <pre>string</pre> | Index name    |
| `collection` | <pre>string</pre> | Collection name    |
| `filters` | <pre>string</pre> | JSON string representing a set of filters following [Koncorde syntax]({{site_base_path}}kuzzle-dsl/1/essential/koncorde) |
| `listener` | <pre>NotificationListener</pre> | Listener function to handle notifications |
| `options` | <pre>RoomOptions</pre> | A class containing subscription options |

### listener

Listener function that will be called each time a new notifications is received.
The listener will receive a [NotificationResult]({{site_base_path}}sdk-reference/csharp/1/essentials/realtime-notifications) as only argument.

### options

Additional subscription options.


| Property   | Type<br/>(default)    | Description                       |
| ---------- | ------- | --------------------------------- |
| `scope` | <pre>string</pre><br/>(`all`) | Subscribe to document entering or leaving the scope</br>Possible values: `all`, `in`, `out`, `none` |
| `users` | <pre>string</pre><br/>(`none`) | Subscribe to users entering or leaving the room</br>Possible values: `all`, `in`, `out`, `none` |
| `subscribeToSelf` | <pre>bool</pre><br/>(`true`) | Subscribe to notifications fired by our own queries |
| `volatile` | <pre>string</pre><br/>(`null`) | JSON string representing subscription information, used in [user join/leave notifications]({{site_base_path}}api/1/volatile-data) |

## Return

Return the room ID.

## Return

Return the room ID.

## Return

Return the room ID.


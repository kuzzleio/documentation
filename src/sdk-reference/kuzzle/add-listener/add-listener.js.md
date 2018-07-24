```javascript
/**
 * @param {string} event
 * @param {function} callback
 * @returns {Kuzzle} this
 */
addListener(event, callback)
```

## Arguments

| Argument | Type | Description | Required |
|--------|------|-------------|------------ |
| `event` | string | One of the event described in the `Event Handling` section of this documentation  | yes |
| `callback` | function | The function to call each time one of the registered event is triggered | yes |

## Return

The same `Kuzzle` instance.

## Usage

[code-example=add-listener]

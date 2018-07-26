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
| `event` | string | One of the event described in the [Event Handling]({{ site_base_path }}sdk-reference/essentials/event-handling) section of this documentation  | yes |
| `callback` | function | The function to call each time one of the registered event is triggered | yes |

## Return

The Kuzzzle` instance.

## Usage

[code-example=add-listener]

## Signature

``` javascript
/**
 * @param {string} index
 * @param {object} [options]
 * @returns {Promise.<Object>}
 */
refresh(index, options = null)
```

## Arguments

| Arguments     | Type        | Description                         | Required
|---------------|-------------|-------------------------------------|----------
| ``options``   | Object      | An object containing query options. | no

### __Options__

Additional query options

| Property | Type    | Description                       | Default |
| -------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true` |

## Resolve

Resolves to an object containing the refresh status on shards.

| Name | Type | Description
|------|------|-------------
| _shards | object | Refresh status on shards, contain 3 properties : total, successful and failed

## Usage

[code-example=refresh]
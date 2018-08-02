## Signature

``` javascript
/**
 * @param {string} index
 * @param {boolean} autoRefresh
 * @param {object} [options]
 * @returns {Promise.<Object>}
 */
setAutoRefresh(index, autoRefresh, options = null)
```

## Arguments

| Arguments     | Type        | Description                         | Required
|---------------|-------------|-------------------------------------|----------
| ``index``     | String      | Index name | yes
| ``autoRefresh``| Boolean    | autoRefresh flag | yes
| ``options``   | Object      | An object containing query options | no

### __Options__

Additional query options

| Property | Type    | Description                       | Default |
| -------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true` |

## Resolve

Resolve to an object containing the new value of autorefresh flag.

| Name | Type | Description
|------|------|-------------
| response | boolean | new value for the autorefresh flag of the index


## Usage

[code-example=setAutoRefresh]
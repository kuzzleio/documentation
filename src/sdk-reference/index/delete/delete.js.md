## Signature

``` javascript
/**
 * @param {string} index
 * @param {object} [options]
 * @returns {Promise.<object>}
 */
delete(index, options = null)
```

## Arguments

| Arguments     | Type        | Description              | Required 
|---------------|-------------|--------------------------|-----------
| ``index``     | String      | Index name               | yes
| ``options``   | Object      | An object containing query options. | no

### __Options__

Additional query options

| Property | Type    | Description                       | Default |
| -------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`    |

## Resolve

Resolves to an object containing the index deletion status.

| Name | Type | Description
|------|------|-------------
| acknowledged | boolean | indicates whether the index was successfully deleted in the Elastic cluster

## Usage

[code-example=delete]

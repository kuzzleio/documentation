## Signature

``` javascript
/**
 * @param {string} index
 * @param {object} [options]
 * @returns {Promise.<Object>}
 */
refreshInternal(index, options = null)
```

## Arguments

| Arguments     | Type        | Description                         | Required
|---------------|-------------|-------------------------------------|----------
| ``index``   | string      | An object containing query options. | no
| ``options``   | Object      | An object containing query options. | no

### __Options__

Additional query options

| Property | Type    | Description                       | Default |
| -------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true` |

## Resolve

Resolves to an object containing the refresh status.

| Name | Type | Description
|------|------|-------------
| acknowledged | boolean | indicates whether the refresh on internal index was successful or not

## Usage

[code-example=refreshInternal]
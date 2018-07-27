## Signature

``` javascript
/**
 * @param {string} index
 * @param {object} [options]
 * @returns {Promise.<Boolean>}
 */
exists(index, options = null)
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

Resolves to a `boolean` that indicate whether the index exists or not.

## Usage

[code-example=exists]
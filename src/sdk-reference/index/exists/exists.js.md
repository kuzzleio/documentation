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

| Arguments     | Type        | Description |
|---------------|-------------|----------------------------------------|
| ``index``     | String      | Index name |
| ``options``   | Object | An object containing query options. |

### __Options__

Query options details :

| Property | Type    | Description                       | Default |
| -------- | ------- | --------------------------------- | ------- |
| queuable | boolean | Make this request queuable or not | true    |

## Resolve

On resolve, returns a boolean that indicate whether the index exists or not.

## Usage

[code-example=exists]
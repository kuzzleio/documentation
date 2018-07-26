## Signature

``` javascript
/**
 * @param {object} [options]
 * @returns {Promise.<Array>}
 */
list(options = null)
```

## Arguments

| Arguments     | Type        | Description                         | Required
|---------------|-------------|-------------------------------------|----------
| ``options``   | Object      | An object containing query options. | no

### __Options__

Additional query options

| Property | Type    | Description                       | Default |
| -------- | ------- | --------------------------------- | ------- |
| queuable | boolean | Make this request queuable or not | true    |

## Resolve

Resolves to an `Array` with the list of indexes names present in Kuzzle

## Usage

[code-example=getAutoRefresh]
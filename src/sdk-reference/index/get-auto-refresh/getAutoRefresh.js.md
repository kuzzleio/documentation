## Signature

``` javascript
/**
 * @param {string} index
 * @param {object} [options]
 * @returns {Promise.<Boolean>}
 */
getAutoRefresh(index, options = null)
```

## Arguments

| Arguments     | Type        | Description |
|---------------|-------------|----------------------------------------|
| ``index``     | String      | Represent the index name |
| ``options``   | JSON Object | An object containing query options. |

### __Options__

Query options details :

| Property | Type    | Description                       | Default |
| -------- | ------- | --------------------------------- | ------- |
| queuable | boolean | Make this request queuable or not | true    |

## Resolve

On resolve, returns a boolean that indicate the status of the **autoRefresh** flag.

## Usage

[code-example=getAutoRefresh]
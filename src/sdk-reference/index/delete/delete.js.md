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

On resolve, returns an object with the index deletion status.

| Name | Type | Description
|------|------|-------------
| acknowledged | boolean | indicates whether the index was successfully deleted in the Elastic cluster

## Usage

[code-example=delete]

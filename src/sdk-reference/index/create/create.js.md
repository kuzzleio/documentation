## Signature

```javascript
/**
 * @param {string} index
 * @param {object} [options]
 * @returns {Promise.<object>}
 */
create(index, options = null)
```

## Arguments

| Arguments     | Type        | Description              | Required 
|---------------|-------------|--------------------------|-----------
| ``index``     | String      | Represent the index name | yes
| ``options``   | JSON Object | An object containing query options. | no

### __Options__

Query options details :

| Property | Type    | Description                       | Default |
| -------- | ------- | --------------------------------- | ------- |
| queuable | boolean | Make this request queuable or not | true    |

## Resolve

On resolve, returns an object with the index creation status.

| Name | Type | Description
|------|------|-------------
| acknowledged | boolean | indicates whether the index was successfully created in the Elastic cluster
| shards_acknowledged | boolean | indicates whether the requisite number of shard copies were started for each shard in the index before timing out

## Usage

[code-example=create]

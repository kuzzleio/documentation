```javascript
/**
  * @param {Object} [options]
  * @returns {Promise.<Object>}
  */
getCurrentUser(options = {})
```

## Arguments

|   Arguments    |      Type      | Description                       |  Required  |             
| :------------- | :------------- | --------------------------------- |:---------- |
|    `options`   |  JSON Object   |An object containing query options |   no       |

### __Options__

| Property | Type    | Description                       | Default |
| -------- | ------- | --------------------------------- | ------- |
| queuable | boolean | Make this request queuable or not | true    |

## Resolve

On resolve, the response object is an `User` object, which is a representation of a Kuzzle user.

## Reject

Reject with a [Kuzzle error]({{ site_base_path }}sdk-reference/essentials/errors).

## Usage

[code-example=get-current-user]

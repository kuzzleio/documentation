```javascript
/**
 * @param {object} request
 * @param {object} [options]
 * @returns {Promise.<object>}
 */
query(request, options = {})
```

## Arguments

| Argument | Type | Description | Required |
|--------|------|-------------|------------ |
| `request` | object | API request options | yes |
| `options` | object | Optional query options | no |

### __request__

All properties necessary for the Kuzzle API can be added in the request object.  
The following properties are the most common.  

| Property | Type    | Description  | Required |
| -------- | ------- | ------------ | -------- |
| `controller` | string | Controller name | yes |
| `action` | string | Action name | yes |
| `body` | object | Query body for this action | no |
| `index` | string | Index name for this action | no |
| `collection` | string | Collection name for this action | no |
| `_id` | string | id for this action | no |
| `volatile` | object | Additional informations to send to Kuzzle | no |

### __options__

An `object` containing query options.

| Property | Type    | Description                       | Default |
| -------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | true    |

## Resolve

Resolve to the raw Kuzzle API response. See the [API Documentation]({{ site_base_path }}api-documentation).

## Usage

[code-example=query]

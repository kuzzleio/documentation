```java
KuzzleResponse query(KuzzleRequest request)
KuzzleResponse query(KuzzleRequest request, QueryOptions options)
```

## Arguments

| Argument | Type | Description | Required |
|--------|------|-------------|------------ |
| `request` | KuzzleRequest | API request options | yes |
| `options` | QueryOptions | Additional query options | no |


### __request__

A `KuzzleRequest` object containing the properties required for the Kuzzle API request.  
Each property can be accessed with standard getter/setter.  
The following properties are the most common.  

| Property | Type    | Description  | Required |
| -------- | ------- | ------------ | -------- |
| `controller` | String | Controller name | yes |
| `action` | String | Action name | yes |
| `body` | String | JSON query body for this action | no |
| `index` | String | Index name for this action | no |
| `collection` | String | Collection name for this action | no |
| `id` | String | id for this action | no |
| `volatile` | String | JSON string representing additional informations to send to Kuzzle | no |

### __options__

A `QueryOptions` object containing the additional options for this request.  
Each property can be accessed with standard getter/setter.  
The following properties are the most common.  

| Property | Type    | Description                       | Default |
| -------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | true  |

## Return

A `KuzzleResponse` object containing the Kuzzle API response. See the [API Documentation]({{ site_base_path }}api-documentation).  
The following properties are the most common.  

| Property | Type    | Description                       |
| -------- | ------- | --------------------------------- |
| `requestId` | String | Request unique id |
| `result` | String | Raw JSON result |
| `error` | String | Error message |
| `status` | int | Request status (eg: 200, 403, etc.) |

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/essentials/error-handling).

## Usage

[code-example=query]

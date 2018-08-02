```cpp
kuzzle_response* query(kuzzle_request* query, query_options* options = nullptr)
```

## Arguments

| Argument | Type | Description | Required |
|--------|------|-------------|------------ |
| `request` | kuzzle_request* | API request options | yes |
| `options` | query_options* | Additional query options | no |

### __request__

Properties required for the Kuzzle API can be set in the [kuzzle_request](https://github.com/kuzzleio/sdk-go/blob/1.x/internal/wrappers/headers/kuzzlesdk.h#L51) struct.  
The following properties are the most common.  

| Property | Type    | Description  | Required |
| -------- | ------- | ------------ | -------- |
| `controller` | const char* | Controller name | yes |
| `action` | const char* | Action name | yes |
| `body` | const char* | JSON query body string for this action | no |
| `index` | const char* | Index name for this action | no |
| `collection` | const char* | Collection name for this action | no |
| `id` | const char* | id for this action | no |
| `volatiles` | const char* | JSON string representing additional informations to send to Kuzzle | no |

### __options__

A pointer to a [query_option](https://github.com/kuzzleio/sdk-go/blob/1.x/internal/wrappers/headers/kuzzlesdk.h#L169) containing additional query options.  
The following properties are the most common.  

| Property | Type    | Description                       | Default |
| -------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | true  |

## Return

A [kuzzle_response](https://github.com/kuzzleio/sdk-go/blob/1.x/internal/wrappers/headers/kuzzlesdk.h#L445) containing the Kuzzle API response. See the [API Documentation]({{ site_base_path }}api-documentation).  
The following properties are the most common.  

| Property | Type    | Description                       |
| -------- | ------- | --------------------------------- |
| `request_id` | char* | Request unique id |
| `result` | char* | Raw JSON result |
| `error` | char* | Error message |
| `status` | int | Request status (eg: 200, 403, etc.) |

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/essentials/error-handling).

## Usage

[code-example=query]

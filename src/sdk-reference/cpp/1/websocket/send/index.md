---
layout: sdk.html.hbs
title: send
description: Base method to send API query to Kuzzle
---

# send

Base method used to send queries to Kuzzle, following the [API Documentation]({{ site_base_path }}api/1).

## Signature

```cpp
kuzzleio::kuzzle_response* send(const std::string& query, kuzzleio::query_options *options, const std::string& request_id);
```

## Arguments

| Argument  | Type             | Description
| --------- | ---------------- | ------------------------
| `query` | <pre>const std::string&</pre> | API request
| `options` | <pre>kuzzleio::query_options\*</pre>  | Additional query options
| `request_id` | <pre>const std::string&</pre> | the request_id of the request if you want to set one

### request

Properties required for the Kuzzle API can be set in the [kuzzle_request](https://github.com/kuzzleio/sdk-c/blob/1-dev/include/internal/kuzzle_structs.h#L195) struct.
The following properties are the most common.

| Property     | Type         | Description 
| ------------ | ------------ | ------------------------------------------------------------------ |
| `controller` | <pre>const char\*</pre> | Controller name                                          |
| `action`     | <pre>const char\*</pre> | Action name                                              |
| `body`       | <pre>const char\*</pre> | Query body, in JSON format                               |
| `index`      | <pre>const char\*</pre> | Data index name                                          |
| `collection` | <pre>const char\*</pre> | Data collection name                                     |
| `id`         | <pre>const char\*</pre> | Unique identifier                                        |
| `volatiles`  | <pre>const char\*</pre> | Additional, non-meaningful information (JSON format)     |

### options

A pointer to a [query_option](https://github.com/kuzzleio/sdk-c/blob/master/include/internal/kuzzle_structs.h#L129) containing additional query options
The following properties are the most common.

| Property   | Type    | Description                       |
| ---------- | ------- | --------------------------------- |
| `queuable` | bool(true) |  If true, queues the request during downtime, until connected to Kuzzle again |

### request_id

A string representing the request Id.| Property     | Type         | Description 
| ------------ | ------------ | ------------------------------------------------------------------ |
| `controller` | <pre>const char\*</pre> | Controller name                                          |
| `action`     | <pre>const char\*</pre> | Action name                                              |
| `body`       | <pre>const char\*</pre> | Query body, in JSON format                               |
| `index`      | <pre>const char\*</pre> | Data index name                                          |
| `collection` | <pre>const char\*</pre> | Data collection name                                     |
| `id`         | <pre>const char\*</pre> | Unique identifier                                        |
| `volatiles`  | <pre>const char\*</pre> | Additional, non-meaningful information (JSON format)     |

## Return

A [kuzzle_response](https://github.com/kuzzleio/sdk-c/blob/master/include/internal/kuzzle_structs.h#L152) containing the Kuzzle API response. See the [API Documentation]({{ site_base_path }}api/1/kuzzle-response).
The following properties are the most common.

| Property     | Type   | Description                         |
| ------------ | ------ | ----------------------------------- |
| `request_id` | <pre>const char\*</pre>| Request unique id                   |
| `result`     | <pre>const char\*</pre> | Raw JSON result                     |
| `error`      | <pre>const char\*</pre> | Error message                       |
| `status`     | <pre>int</pre>    | Request status (eg: 200, 403, etc.) |
